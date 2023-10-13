package com.diiage.bookit.ui.screens.filter

import android.app.Application
import androidx.compose.runtime.Composable
import com.diiage.bookit.ui.core.ViewModel
import java.time.LocalDate

class FilterViewModel(application: Application) : ViewModel<FilterState>(FilterState(), application) {

   init {
        state.value.materials.forEach() { _ ->
           updateState { copy(checked = checked + false) }
        }
   }
   private fun resetFilter() {
      updateState { copy(capacity = 1, checked = checked.map { false }) }
   }

   private fun updateCapacity(capacity: Int) {
      updateState { copy(capacity = capacity) }
   }

   private fun updateChecked(index: Int, value: Boolean) {
         updateState {
            val updated = checked.toMutableList()
            updated[index] = value
            copy(checked = updated)
         }
     }

   fun handleAction(action: FilterAction) {
      when (action) {
         is FilterAction.ResetFilters -> resetFilter()
         is FilterAction.UpdateCapacity -> updateCapacity(action.capacity)
         is FilterAction.UpdateChecked -> updateChecked(action.index ,action.checked)
      }
   }

}

data class FilterState(
   val isLoading: Boolean = true,
   val error: String? = null,
   val capacity: Int = 1,
   val checked: List<Boolean> = emptyList(),
   val materials: List<String> = listOf("Machine à café", "Tableau blanc", "Télévision", "Gel hydroalcoolique")
)

sealed interface FilterAction {
   object ResetFilters : FilterAction
   data class UpdateCapacity(val capacity: Int) : FilterAction
   data class UpdateChecked(val index: Int, val checked: Boolean) : FilterAction
}