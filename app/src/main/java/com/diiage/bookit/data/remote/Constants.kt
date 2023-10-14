package com.diiage.bookit.data.remote

sealed class Preferences(val name: String){
    object AccessToken: Preferences("access_token")
    object RefreshToken: Preferences("refresh_token")
    object User: Preferences("user")
}

sealed class Url(val path: String){
    object BaseUrl: Url("http://192.168.1.14:45455")
    object Login: Url("/api/auth/login")
    object Signup: Url("/api/auth/signup")

    // Bookings
    object Bookings: Url("/bookings")
    object Booking: Url("/bookings/{id}")
    object BookingsAvailable: Url("/bookings/available")
    object CreateBookable: Url("/bookables")

    // Slots
    object Slots: Url("/api/slots")
}

sealed class ErrorMessage(val message: String){
    object IncorrectPassword: ErrorMessage("Email ou mot de passe incorrect.")
    object SignupError: ErrorMessage("Impossible de créer le compte.")
    object ValidationError: ErrorMessage("Veuillez vérifier les champs saisis.")
    object ServerError: ErrorMessage("Une erreur est survenue.")
}