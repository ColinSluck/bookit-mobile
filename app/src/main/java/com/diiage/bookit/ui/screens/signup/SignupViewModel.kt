package com.diiage.bookit.ui.screens.signup

import android.app.Application
import androidx.lifecycle.viewModelScope
import androidx.navigation.ActivityNavigator
import com.diiage.bookit.data.remote.API
import com.diiage.bookit.domain.models.Credentials
import com.diiage.bookit.domain.models.Signup
import com.diiage.bookit.domain.repositories.PreferenceRepository
import com.diiage.bookit.ui.core.NavigationEvent
import com.diiage.bookit.ui.core.ViewModel
import com.diiage.bookit.ui.core.functions.isValidSignupForm
import org.koin.core.component.inject
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

class SignupViewModel (application: Application) : ViewModel<SignupState>(SignupState(), application) {

    private val api: API by inject()

    private val preferencesRepository: PreferenceRepository by inject()

    fun handleAction(action: SignupAction) {
        when (action) {
            is SignupAction.OnSignup -> signup(action.signup)
            is SignupAction.OnLoginClick -> sendEvent(NavigationEvent.NavigateToLogin)
        }
    }

    private fun signup(signup: Signup) {
        viewModelScope.launch {
            if(!isValidSignupForm(signup)) return@launch

            val user = api.signup(signup) ?: return@launch

            preferencesRepository.save("access_token", user.accessToken)
            preferencesRepository.save("refresh_token", user.refreshToken)

            val userString = Json.encodeToString(user)
            preferencesRepository.save("user", userString)

            sendEvent(NavigationEvent.NavigateToHome)
        }
    }
}

data class SignupState(
    val email: String = "",
)

sealed interface SignupAction {
    data class OnSignup(val signup: Signup) : SignupAction
    data class OnLoginClick(val clicked: Boolean? = null) : SignupAction
}