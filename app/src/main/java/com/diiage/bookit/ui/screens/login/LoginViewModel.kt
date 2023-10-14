package com.diiage.bookit.ui.screens.login

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.diiage.bookit.data.remote.ErrorMessage
import com.diiage.bookit.domain.exceptions.LoginException
import com.diiage.bookit.domain.models.Credentials
import com.diiage.bookit.domain.repositories.AuthRepository
import com.diiage.bookit.ui.core.NavigationEvent
import com.diiage.bookit.ui.core.ViewModel
import org.koin.core.component.inject
import kotlinx.coroutines.launch

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
            updateState { copy(isLoading = true) }

            try {
                authRepository.login(credentials)

                sendEvent(NavigationEvent.NavigateToHome)
            } catch (e: LoginException) {
                when (e) {
                    LoginException.IncorrectPassword ->
                        updateState { copy(error = ErrorMessage.IncorrectPassword.message, isLoading = false) }

                    LoginException.ValidationError ->
                        updateState { copy(error = ErrorMessage.ValidationError.message, isLoading = false) }

                    LoginException.ServerError ->
                        updateState { copy(error = ErrorMessage.ServerError.message, isLoading = false) }

                }
            }
        }
    }

}
data class LoginState(
    val isLoading : Boolean = false,
    val error: String? = null,
)

sealed interface LoginAction {
    data class OnConnect(val credentials: Credentials) : LoginAction
    object OnSignup : LoginAction
}