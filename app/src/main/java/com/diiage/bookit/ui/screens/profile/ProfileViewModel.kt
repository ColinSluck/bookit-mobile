package com.diiage.bookit.ui.screens.profile

import android.app.Application
import com.diiage.bookit.domain.models.Credentials
import com.diiage.bookit.domain.models.User
import com.diiage.bookit.domain.repositories.PreferenceRepository
import com.diiage.bookit.ui.core.ViewModel
import kotlinx.serialization.json.Json
import org.koin.core.component.inject


class ProfileViewModel(application: Application) : ViewModel<ProfileState>(ProfileState(), application) {

    private val preferencesRepository: PreferenceRepository by inject()

    init {
        val userString = preferencesRepository.get("user")

        if(userString != null) {
            val user: User = Json.decodeFromString(userString)

            updateState { copy(user = user) }
        }
    }

    fun handleAction(action: ProfileAction) {
        when (action) {
            is ProfileAction.OnDisconnect -> disconnect()
        }
    }

    private fun disconnect() {
        preferencesRepository.remove("access_token")
        preferencesRepository.remove("refresh_token")
        preferencesRepository.remove("user")
    }

}

data class ProfileState(
    val user: User = User(0, "", "", "", "", "", "", "", "")
)

sealed interface ProfileAction {
    object OnDisconnect : ProfileAction
}