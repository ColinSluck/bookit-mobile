package com.diiage.bookit.ui.screens.home

import android.app.Application
import com.diiage.bookit.ui.core.ViewModel

class HomeViewModel(application: Application) : ViewModel<HomeState>(HomeState(), application)

data class HomeState(
   val isLoading: Boolean = true,
   val error: String? = null
)