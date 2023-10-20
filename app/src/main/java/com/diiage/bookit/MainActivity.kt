package com.diiage.bookit

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.diiage.bookit.domain.models.Bookable
import com.diiage.bookit.domain.models.Search
import com.diiage.bookit.domain.repositories.PreferenceRepository
import com.diiage.bookit.ui.core.Destination
import com.diiage.bookit.ui.core.composable
import com.diiage.bookit.ui.core.composables.navbar.Navbar
import com.diiage.bookit.ui.core.theme.BookItTheme
import com.diiage.bookit.ui.screens.bookable.BookableScreen
import com.diiage.bookit.ui.screens.bookings.BookingsScreen
import com.diiage.bookit.ui.screens.createBookable.CreateBookableScreen
import com.diiage.bookit.ui.screens.filter.FilterScreen
import com.diiage.bookit.ui.screens.home.HomeScreen
import com.diiage.bookit.ui.screens.login.LoginScreen
import com.diiage.bookit.ui.screens.profile.ProfileScreen
import com.diiage.bookit.ui.screens.search.SearchScreen
import com.diiage.bookit.ui.screens.signup.SignupScreen
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val preferenceRepository: PreferenceRepository by inject()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContent {
            BookItTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                    content = { MainContent(preferenceRepository) }
                )
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainContent(preferenceRepository: PreferenceRepository) {



    val navController = rememberNavController()

    val startDestination = if (preferenceRepository.get("access_token") != null) Destination.Home else Destination.Login

    Scaffold(bottomBar = {
        if (shouldShowNavBar(navController)) {
            Navbar(navController)
        }
    }) {
        Box(modifier = if (shouldShowNavBar(navController)) Modifier.padding(bottom = 87.dp) else Modifier) {
            NavHost(navController = navController, startDestination = startDestination.route) {

                composable(Destination.Profile) { ProfileScreen(navController) }

                composable(Destination.Bookings) { BookingsScreen(navController) }


                composable(Destination.Home) { HomeScreen(navController) }

                composable(Destination.Filter) { FilterScreen(navController) }

                composable(Destination.Login) { LoginScreen(navController) }

                composable(Destination.Signup) { SignupScreen(navController) }

                composable(Destination.CreateBookable) { CreateBookableScreen(navController) }

                composable(
                    destination = Destination.Bookable(),
                ) { backStackEntry ->
                    BookableScreen(
                        navController = navController,
                        id =  backStackEntry.arguments?.getString("bookable") ?: "0"
                    )
                }


                composable(
                    destination = Destination.Search(),
                ) { backStackEntry ->
                    SearchScreen(
                        navController = navController,
                        search = backStackEntry.arguments?.getString("search") ?: Json.encodeToString(Search())
                    )
                }
            }
        }
    }
}

@Composable
fun shouldShowNavBar(navController: NavHostController): Boolean {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination
    val routesWithoutNavBar = setOf(
        Destination.Filter.route,
        Destination.Login.route,
        Destination.Signup.route,
        Destination.CreateBookable.route
    )

    return currentRoute?.route !in routesWithoutNavBar
}