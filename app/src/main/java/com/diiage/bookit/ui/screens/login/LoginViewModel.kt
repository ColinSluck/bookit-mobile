package com.diiage.bookit.ui.screens.login

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.diiage.bookit.data.remote.repositories.API
import com.diiage.bookit.domain.models.Credentials
import com.diiage.bookit.ui.core.ViewModel
import org.koin.core.component.inject
import kotlinx.coroutines.launch

class LoginViewModel (application: Application) : ViewModel<LoginState>(LoginState(), application) {

    private val api: API by inject()

    fun handleAction(action: LoginAction) {
        when (action) {
            is LoginAction.OnConnect -> login(action.credentials)
        }
    }

    fun login(credentials: Credentials) {
        viewModelScope.launch {
            val user = api.login(credentials)
        }
    }

}

data class LoginState(
    val email: String = "",
    val password: String = "",
)

sealed interface LoginAction {
    data class OnConnect(val credentials: Credentials) : LoginAction
}