package com.diiage.bookit.ui.core

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.Navigator
import androidx.navigation.compose.composable
import androidx.navigation.navArgument


sealed class NavigationEvent {
    object NavigateToHome : NavigationEvent()
    object NavigateToLogin : NavigationEvent()
    object NavigateToSignup : NavigationEvent()
    object NavigateToCreateBookable : NavigationEvent()
    object NavigateToProfile : NavigationEvent()
}

fun NavGraphBuilder.composable(
    destination: Destination,
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable() (AnimatedContentScope.(NavBackStackEntry) -> Unit)
) = composable(
    route = destination.route,
    arguments = destination.arguments,
    deepLinks = deepLinks,
    content = content
)

sealed class Destination(val route: String, val arguments: List<NamedNavArgument> = emptyList()) {

    object Home: Destination(route = "home")
    object Bookings: Destination(route = "bookings")
    object Profile: Destination(route = "profile")
    object Login: Destination(route = "login")
    object Signup: Destination(route = "signup")
    //object Bookable: Destination(route = "bookable")
    object Filter: Destination(route = "filter")
    object CreateBookable: Destination(route = "createBookable")

    class Bookable(bookable: String = "{bookable}"): Destination(
        route = "bookable/${bookable}",
        arguments = listOf(navArgument("bookable") { type = NavType.StringType })
    )
    class Search(search: String = "{search}"): Destination(
        route = "search/${search}",
        arguments = listOf(navArgument("search") { type = NavType.StringType })
    )
}

fun NavController.navigate(
    destination: Destination,
    navOptions: NavOptions? = null,
    navigatorExtras: Navigator.Extras? = null
) = navigate(
    route = destination.route,
    navOptions = navOptions,
    navigatorExtras = navigatorExtras
)