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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.diiage.bookit.ui.core.Screen
import com.diiage.bookit.ui.core.composables.navbar.Navbar
import com.diiage.bookit.ui.core.theme.BookItTheme
import com.diiage.bookit.ui.screens.bookable.BookableScreen
import com.diiage.bookit.ui.screens.bookings.BookingsScreen
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

    Scaffold(bottomBar = { Navbar(navController) }) {
        Box(modifier = Modifier.padding(bottom = 87.dp)) {
            NavHost(navController = navController, startDestination = Screen.Home.route) {

                composable(Screen.Profil.route) { ProfilScreen(navController) }

                composable(Screen.Bookings.route) { BookingsScreen(navController) }

                composable(Screen.Bookable.route) { BookableScreen(navController) }

                composable(Screen.Home.route) { HomeScreen() }

            }
        }
    }
}