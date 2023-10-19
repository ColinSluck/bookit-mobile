package com.diiage.bookit.ui.screens.filter

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.diiage.bookit.data.remote.ErrorMessage
import com.diiage.bookit.domain.models.Material
import com.diiage.bookit.domain.models.Search
import com.diiage.bookit.domain.models.Slot
import com.diiage.bookit.domain.repositories.MaterialRepository
import com.diiage.bookit.domain.repositories.SearchRepository
import com.diiage.bookit.domain.repositories.SlotRepository
import com.diiage.bookit.ui.core.Destination
import com.diiage.bookit.ui.core.ViewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.koin.core.component.inject
import java.time.LocalDate

class FilterViewModel(application: Application) : ViewModel<FilterState>(FilterState(), application) {

    private val slotRepository: SlotRepository by inject()
    private val materialRepository: MaterialRepository by inject()

    init {
        Log.d("FilterViewModel", "FilterViewModel: init")
        getSlots()
        getMaterials()
    }

    private fun getSlots() {
      viewModelScope.launch {
            try {
                val slots = slotRepository.getSlots()
                updateState { copy(slots = slots.data) }
            } catch (e: Exception) {
                updateState { copy(error = ErrorMessage.ServerError.message) }
            }
            updateState { copy(isLoading = false) }
      }
    }

    private fun getMaterials() {
        viewModelScope.launch {
            updateState { copy(isLoading = true) }
            try {
                val response = materialRepository.getMaterials(state.value.currentBookableTypeId, state.value.materialPage,10)
                updateState {copy(materials = materials + response.data, totalMaterial = response.totalCount)}
            } catch (e: Exception) {
                updateState { copy(error = ErrorMessage.ServerError.message) }
            }
            updateState { copy(isLoading = false) }
        }
    }

    private fun resetFilter() {
      updateState { copy(capacity = 1, materialCheckedIds = emptyList()) }
    }

    private fun updateCapacity(capacity: Int) {
      updateState { copy(capacity = capacity) }
    }

    private fun updateChecked(index: Int, value: Boolean) {
        val materialId = state.value.materials[index].id
        if (value) {
            // Si coché, ajoutez l'ID à la liste
            updateState {
                val updated = materialCheckedIds.toMutableList()
                updated.add(materialId)
                copy(materialCheckedIds = updated)
            }
        } else {
            // Sinon, retirez l'ID de la liste
            updateState {
                val updated = materialCheckedIds.toMutableList()
                updated.remove(materialId)
                copy(materialCheckedIds = updated)
            }
        }
    }

    fun handleAction(action: FilterAction) {
        when (action) {
            is FilterAction.ResetFilters -> resetFilter()
            is FilterAction.UpdateCapacity -> updateCapacity(action.capacity)
            is FilterAction.UpdateChecked -> updateChecked(action.index ,action.checked)
            is FilterAction.SelectSlot -> updateState { copy(selectedSlotId = action.slotId) }
            is FilterAction.LoadMoreMaterials -> loadMoreMaterial()
            is FilterAction.SelectDate -> updateState { copy(selectedDate = action.selectedDate) }
            is FilterAction.Search -> search()
        }
    }

    private fun loadMoreMaterial() {
        updateState { copy(materialPage = materialPage + 1) }
        getMaterials()
    }

    private fun search() {
        val search = Search(
            minCapacity = state.value.capacity,
            slotId = state.value.selectedSlotId,
            date = state.value.selectedDate.toString(),
            bookableTypeId = state.value.currentBookableTypeId,
            materialIds = state.value.materialCheckedIds
        )

        updateState { copy(search = search) }

        val searchJson: String = Json.encodeToString(search)

        sendEvent(Destination.Search(searchJson))
    }

}

data class FilterState(
    val currentBookableTypeId: Int = 2,
    val isLoading: Boolean = true,
    val error: String? = null,
    val capacity: Int = 1,
    val slots: List<Slot> = emptyList(),
    val selectedSlotId: Int = 33,
    val materialPage: Int = 1,
    val materials: List<Material> = emptyList(),
    val materialCheckedIds: List<Int> = emptyList(),
    val totalMaterial: Int = 0,
    val selectedDate: LocalDate? = null,
    val search: Search = Search()
)

sealed interface FilterAction {
   object ResetFilters : FilterAction
   data class UpdateCapacity(val capacity: Int) : FilterAction
   data class UpdateChecked(val index: Int, val checked: Boolean) : FilterAction
    data class SelectSlot(val slotId: Int) : FilterAction
    object LoadMoreMaterials : FilterAction
    data class SelectDate(val selectedDate: LocalDate) : FilterAction
    object Search : FilterAction
}