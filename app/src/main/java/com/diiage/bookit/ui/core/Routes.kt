package com.diiage.bookit.ui.core

sealed class Screen(val route: String){
    object Home: Screen("home")
    object Bookings: Screen("bookings")
    object Profil: Screen("profil")
    object Login: Screen("login")
    object Signup: Screen("signup")
}