package com.diiage.bookit.ui.screens.home

import android.app.Application
import android.view.View
import androidx.lifecycle.ViewModel

class HomeViewModel(application: Application) : ViewModel() {

}

data class HomeState(
   val isLoading: Boolean = true,
   val error: String? = null
)