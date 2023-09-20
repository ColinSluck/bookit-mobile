package com.diiage.bookit.ui.screens.login

import android.app.Application
import com.diiage.bookit.ui.core.ViewModel

class LoginViewModel (application: Application) : ViewModel<LoginState>(LoginState(), application) {

}

data class LoginState(
    val email: String = "",
    val password: String = "",
)