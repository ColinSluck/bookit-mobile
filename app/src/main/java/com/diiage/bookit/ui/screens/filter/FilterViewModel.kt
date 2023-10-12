package com.diiage.bookit.ui.screens.filter

import android.app.Application
import com.diiage.bookit.ui.core.ViewModel

class FilterViewModel(application: Application) : ViewModel<FilterState>(FilterState(), application) {

}

data class FilterState(
   val isLoading: Boolean = true,
   val error: String? = null
)