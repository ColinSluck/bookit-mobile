package com.diiage.bookit.ui.screens.home

import android.app.Application
import com.diiage.bookit.domain.models.User
import com.diiage.bookit.domain.repositories.PreferenceRepository
import com.diiage.bookit.ui.core.ViewModel
import kotlinx.serialization.json.Json
import org.koin.core.component.inject

class HomeViewModel(application: Application) : ViewModel<HomeState>(HomeState(), application) {

   private val preferencesRepository: PreferenceRepository by inject()

   init {
      val userString = preferencesRepository.get("user")

      if(userString != null) {
         val user: User = Json.decodeFromString(userString)

         updateState { copy(firstName = user.firstName) }
      }
   }
}

data class HomeState(
   val isLoading: Boolean = true,
   val error: String? = null,
   val firstName : String = "",
)