package com.diiage.bookit.ui.screens.filter

import android.app.Application
import androidx.compose.runtime.Composable
import com.diiage.bookit.ui.core.ViewModel

class FilterViewModel(application: Application) : ViewModel<FilterState>(FilterState(), application) {

   private fun resetFilter() {

   }
   fun handleAction(action: FilterAction) {
      when (action) {
         is FilterAction.ResetFilters -> resetFilter()
      }
   }

}

data class FilterState(
   val isLoading: Boolean = true,
   val error: String? = null,
)

sealed interface FilterAction {
    object ResetFilters : FilterAction
}