package com.diiage.bookit.data.remote

sealed class Preferences(val name: String){
    object AccessToken: Preferences("access_token")
    object RefreshToken: Preferences("refresh_token")
    object User: Preferences("user")
}

sealed class Url(val path: String){
    object BaseUrl: Url("http://10.4.0.100:45455/api")
    object Login: Url("/auth/login")

    // Bookings
    object Bookings: Url("/bookings")
    object Booking: Url("/bookings/{id}")
    object BookingsAvailable: Url("/bookings/available")
    object CreateBookable: Url("/bookables")
}
