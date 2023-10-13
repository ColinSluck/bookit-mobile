package com.diiage.bookit.ui.screens.filter

import android.app.Application
import androidx.compose.runtime.Composable
import com.diiage.bookit.ui.core.ViewModel
import java.time.LocalDate

class FilterViewModel(application: Application) : ViewModel<FilterState>(FilterState(), application) {

   private fun resetFilter() {
      updateState { copy(capacity = 1) }
      updateState { copy(checked = false) }
   }

   private fun updateCapacity(capacity: Int) {
      updateState { copy(capacity = capacity) }
   }

   private fun updateChecked(checked: Boolean) {
      updateState { copy(checked = checked) }
   }

   fun handleAction(action: FilterAction) {
      when (action) {
         is FilterAction.ResetFilters -> resetFilter()
         is FilterAction.UpdateCapacity -> updateCapacity(action.capacity)
         is FilterAction.UpdateChecked -> updateState { copy(checked = action.checked) }
      }
   }

}

data class FilterState(
   val isLoading: Boolean = true,
   val error: String? = null,
   val capacity: Int = 1,
   val checked: Boolean = false,
)

sealed interface FilterAction {
    object ResetFilters : FilterAction
   data class UpdateCapacity(val capacity: Int) : FilterAction
   data class UpdateChecked(val index: Int, val checked: Boolean) : FilterAction
}