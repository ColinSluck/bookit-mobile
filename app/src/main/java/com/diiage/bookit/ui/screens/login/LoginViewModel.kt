package com.diiage.bookit.ui.screens.login

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.diiage.bookit.data.remote.API
import com.diiage.bookit.domain.models.Credentials
import com.diiage.bookit.domain.repositories.PreferenceRepository
import com.diiage.bookit.ui.core.NavigationEvent
import com.diiage.bookit.ui.core.ViewModel
import com.diiage.bookit.ui.core.functions.isValidLoginForm
import org.koin.core.component.inject
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString

class LoginViewModel (application: Application) : ViewModel<LoginState>(LoginState(), application) {

    private val api: API by inject()

    private val preferencesRepository: PreferenceRepository by inject()

    fun handleAction(action: LoginAction) {
        when (action) {
            is LoginAction.OnConnect -> login(action.credentials)
            is LoginAction.OnSignup -> sendEvent(NavigationEvent.NavigateToSignup)
        }
    }

    private fun login(credentials: Credentials) {
        viewModelScope.launch {
            if(!isValidLoginForm(credentials)) return@launch

            val user = api.login(credentials) ?: return@launch

            preferencesRepository.save("access_token", user.accessToken)
            preferencesRepository.save("refresh_token", user.refreshToken)

            val userString = Json.encodeToString(user)
            preferencesRepository.save("user", userString)

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
    data class OnSignup(val signup: Boolean? = null) : LoginAction
}