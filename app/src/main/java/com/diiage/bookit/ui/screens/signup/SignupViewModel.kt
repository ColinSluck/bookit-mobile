package com.diiage.bookit.ui.screens.signup

import android.app.Application
import androidx.lifecycle.viewModelScope
import androidx.navigation.ActivityNavigator
import com.diiage.bookit.data.remote.API
import com.diiage.bookit.data.remote.ErrorMessage
import com.diiage.bookit.domain.exceptions.SignupException
import com.diiage.bookit.domain.models.Credentials
import com.diiage.bookit.domain.models.Signup
import com.diiage.bookit.domain.repositories.AuthRepository
import com.diiage.bookit.domain.repositories.PreferenceRepository
import com.diiage.bookit.ui.core.NavigationEvent
import com.diiage.bookit.ui.core.ViewModel
import com.diiage.bookit.ui.core.functions.isValidSignupForm
import org.koin.core.component.inject
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

class SignupViewModel (application: Application) : ViewModel<SignupState>(SignupState(), application) {

    private val authRepository: AuthRepository by inject()

    fun handleAction(action: SignupAction) {
        when (action) {
            is SignupAction.OnSignup -> signup(action.signup)
            is SignupAction.OnLoginClick -> sendEvent(NavigationEvent.NavigateToLogin)
        }
    }

    private fun signup(signup: Signup) {
        viewModelScope.launch {
            updateState { copy(isLoading = true) }

            try {
                authRepository.signup(signup)

                sendEvent(NavigationEvent.NavigateToHome)
            } catch(e: SignupException) {
                when (e) {
                    SignupException.SignupError ->
                        updateState { copy(error = ErrorMessage.SignupError.message, isLoading = false) }

                    SignupException.ValidationError ->
                        updateState { copy(error = ErrorMessage.ValidationError.message, isLoading = false) }

                    SignupException.ServerError ->
                        updateState { copy(error = ErrorMessage.ServerError.message, isLoading = false) }
                }
            }
        }
    }
}

data class SignupState(
    val isLoading : Boolean = false,
    val error: String? = null,
)

sealed interface SignupAction {
    data class OnSignup(val signup: Signup) : SignupAction
    object OnLoginClick : SignupAction
}