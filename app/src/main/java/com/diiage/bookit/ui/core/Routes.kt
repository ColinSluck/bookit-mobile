package com.diiage.bookit.ui.core

sealed class Screen(val route: String){
    object Home: Screen("home")
    object Bookings: Screen("bookings")
    object Profil: Screen("profil")

    object Bookable : Screen("bookable")
    object Login: Screen("login")
}