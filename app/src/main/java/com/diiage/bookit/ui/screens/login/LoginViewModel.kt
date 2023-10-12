package com.diiage.bookit.ui.screens.login

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.diiage.bookit.data.remote.API
import com.diiage.bookit.domain.models.Credentials
import com.diiage.bookit.domain.repositories.AuthRepository
import com.diiage.bookit.domain.repositories.PreferenceRepository
import com.diiage.bookit.ui.core.NavigationEvent
import com.diiage.bookit.ui.core.ViewModel
import com.diiage.bookit.ui.core.functions.isValidLoginForm
import org.koin.core.component.inject
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

class LoginViewModel (application: Application) : ViewModel<LoginState>(LoginState(), application) {

    private val authRepository: AuthRepository by inject()

    fun handleAction(action: LoginAction) {
        when (action) {
            is LoginAction.OnConnect -> login(action.credentials)
            is LoginAction.OnSignup -> sendEvent(NavigationEvent.NavigateToSignup)
        }
    }

    private fun login(credentials: Credentials) {
        viewModelScope.launch {
            if(!isValidLoginForm(credentials)) return@launch

            authRepository.login(credentials) ?: return@launch

            sendEvent(NavigationEvent.NavigateToHome)
        }
    }


}
data class LoginState(
    val email: String = "",
    val password: String = "",
)

sealed interface LoginAction {
    data class OnConnect(val credentials: Credentials) : LoginAction
    object OnSignup : LoginAction
}