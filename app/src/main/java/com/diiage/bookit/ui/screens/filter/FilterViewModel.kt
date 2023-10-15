package com.diiage.bookit.ui.screens.filter

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.diiage.bookit.domain.models.Slot
import com.diiage.bookit.domain.repositories.SlotRepository
import com.diiage.bookit.ui.core.ViewModel
import kotlinx.coroutines.launch
import org.koin.core.component.inject

class FilterViewModel(application: Application) : ViewModel<FilterState>(FilterState(), application) {

    private val slotRepository: SlotRepository by inject()

   init {
        state.value.materials.forEach() { _ ->
           updateState { copy(checked = checked + false) }
        }

      getSlots()
   }

   private fun getSlots() {
      viewModelScope.launch {
            updateState { copy(slotsIsLoading = true) }
            try {
                val slots = slotRepository.getSlots()
                updateState { copy(slots = slots.data) }
            } catch (e: Exception) {
                updateState { copy(error = e.message) }
            }
            updateState { copy(slotsIsLoading = false) }
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
         is FilterAction.SelectSlot -> updateState { copy(selectedSlotId = action.slotId) }
      }
   }

}

data class FilterState(
   val isLoading: Boolean = true,
   val slotsIsLoading: Boolean = true,
   val error: String? = null,
   val capacity: Int = 1,
   val checked: List<Boolean> = emptyList(),
   val materials: List<String> = listOf("Machine à café", "Tableau blanc", "Télévision", "Gel hydroalcoolique"),
   val slots: List<Slot> = emptyList(),
    val selectedSlotId: Int = 33
)

sealed interface FilterAction {
   object ResetFilters : FilterAction
   data class UpdateCapacity(val capacity: Int) : FilterAction
   data class UpdateChecked(val index: Int, val checked: Boolean) : FilterAction
    data class SelectSlot(val slotId: Int) : FilterAction
}