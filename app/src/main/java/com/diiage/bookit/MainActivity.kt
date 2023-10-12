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
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.diiage.bookit.ui.core.Screen
import com.diiage.bookit.ui.core.composables.navbar.Navbar
import com.diiage.bookit.ui.core.theme.BookItTheme
import com.diiage.bookit.ui.screens.bookable.BookableScreen
import com.diiage.bookit.ui.screens.bookings.BookingsScreen
import com.diiage.bookit.ui.screens.filter.FilterScreen
import com.diiage.bookit.ui.screens.home.HomeScreen
import com.diiage.bookit.ui.screens.profil.ProfilScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContent {
            BookItTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                    content = { MainContent() }
                )
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MainContent() {

    val navController = rememberNavController()

    val currentRoute = navController.currentBackStackEntry?.destination?.route

    Scaffold(bottomBar = {
        if (shouldShowNavBar(navController)) {
            Navbar(navController)
        }
    }) {
        Box(modifier = if (shouldShowNavBar(navController)) Modifier.padding(bottom = 87.dp) else Modifier) {
            NavHost(navController = navController, startDestination = Screen.Home.route) {

                composable(Screen.Profil.route) { ProfilScreen(navController) }

                composable(Screen.Bookings.route) { BookingsScreen(navController) }

                composable(Screen.Bookable.route) { BookableScreen(navController) }

                composable(Screen.Home.route) { HomeScreen(navController) }

                composable(Screen.Filter.route) { FilterScreen(navController) }

            }
        }
    }
}

@Composable
fun shouldShowNavBar(navController: NavHostController): Boolean {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    val routesWithoutNavBar = setOf(
        Screen.Filter.route
    )

    return currentRoute !in routesWithoutNavBar
}