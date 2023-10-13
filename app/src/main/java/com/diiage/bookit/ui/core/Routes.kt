package com.diiage.bookit.ui.core

sealed class Screen(val route: String){
    object Home: Screen("home")
    object Bookings: Screen("bookings")
    object Profile: Screen("profile")
    object Login: Screen("login")
    object Signup: Screen("signup")
    object Bookable: Screen("bookable")
    object Filter: Screen("filter")
    object CreateBookable: Screen("createBookable")
}

sealed class NavigationEvent {
    object NavigateToHome : NavigationEvent()
    object NavigateToLogin : NavigationEvent()
    object NavigateToSignup : NavigationEvent()
    object NavigateToCreateBookable : NavigationEvent()
    object NavigateToProfile : NavigationEvent()
}