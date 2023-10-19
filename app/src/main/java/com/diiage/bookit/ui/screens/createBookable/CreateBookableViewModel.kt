package com.diiage.bookit.ui.screens.createBookable


import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewModelScope
import com.diiage.bookit.data.remote.ErrorMessage
import com.diiage.bookit.domain.models.Bookable
import com.diiage.bookit.domain.models.CreateBookable
import com.diiage.bookit.domain.models.FileItem
import com.diiage.bookit.domain.models.Material
import com.diiage.bookit.domain.repositories.AuthRepository
import com.diiage.bookit.domain.repositories.BookableRepository
import com.diiage.bookit.domain.repositories.MaterialRepository
import com.diiage.bookit.ui.core.Destination
import com.diiage.bookit.ui.core.NavigationEvent
import com.diiage.bookit.ui.core.ViewModel
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import java.io.File

/**
 * CreateBookableViewModel is a ViewModel class for the 'CreateBookable' screen in the Android app.
 *
 * This ViewModel is responsible for managing the data and logic related to creating bookable items,
 * including handling user interactions, making network requests to retrieve materials, and creating
 * bookable items.
 *
 * @param application: The Android Application instance.
 */
class CreateBookableViewModel(application: Application) : ViewModel<CreateBookableState>(CreateBookableState(), application) {

    // Inject dependencies using Koin
    private val bookableRepository: BookableRepository by inject()
    private val materialRepository: MaterialRepository by inject()

    init {
        getMaterials()
    }


    var currentStep by mutableStateOf(0)

    // List of step colors for the UI
    val stepColors = listOf(
        Color(0xFFD9D9D9), Color(0xFFD9D9D9), Color(0xFFD9D9D9), Color(0xFFD9D9D9), Color(0xFFD9D9D9)
    )

    // Function to proceed to the next step
    fun onNextStep() {
            if (currentStep < stepColors.size - 1) {
                currentStep++
            }
    }

    // Function to go back to the previous step or navigate to the profile screen if at the first step
    fun onPreviousStep() {
        if (currentStep > 0) {
            currentStep--
        } else {
            sendEvent(NavigationEvent.NavigateToProfile)
        }
    }

    // Function to finish the process and create a bookable item
    fun onFinish() {
        currentStep = 5

        updateState {
            val bookable = state.value.bookable
            bookable.materialIds = materialCheckedIds
            copy(bookable = bookable)
        }

        viewModelScope.launch {
            updateState { copy(isLoading = true) }
            try {
                val response = bookableRepository.createBookable(state.value.bookable)
                updateState { copy(createdBookable = response) }
                uploadImages()

            } catch (e: Exception) {
                updateState { copy(error = ErrorMessage.ServerError.message) }
            }
            updateState { copy(isLoading = false) }
        }
    }

    private fun uploadImages() {
        val fileItems = state.value.images.mapIndexed { index, uri ->
            val file = File(uri.path)
            FileItem(fieldName = "image_$index", file = file)
        }

        viewModelScope.launch {
            updateState { copy(isLoading = true) }
            try {
                val response = bookableRepository.uploadImages(state.value.createdBookable!!.id, fileItems)
            } catch (e: Exception) {
                Log.e("CreateBookableViewModel", "uploadImages: ${e.message}")
                updateState { copy(error = ErrorMessage.ServerError.message) }
            }
        }
    }

    // Function to handle various user actions
    fun handleAction(action: CreateBookableAction) {
        when (action) {
            is CreateBookableAction.OnCloseClicked -> sendEvent(NavigationEvent.NavigateToProfile)
            is CreateBookableAction.OnNameChanged -> updateState {
                var bookable = state.value.bookable
                bookable.name = action.name
                copy(bookable = bookable)
            }
            is CreateBookableAction.OnLocationChanged -> updateState {
                var bookable = state.value.bookable
                bookable.place = action.location
                copy(bookable = bookable)
            }
            is CreateBookableAction.OnDescriptionChanged -> updateState {
                var bookable = state.value.bookable
                bookable.description = action.description
                copy(bookable = bookable)
            }
            is CreateBookableAction.OnCapacityChanged -> updateState {
                var bookable = state.value.bookable
                bookable.maxCapacity = action.maxCapacity.toInt()
                copy(bookable = bookable)
            }
            is CreateBookableAction.OnUpdateChecked -> updateChecked(action.index, action.value)
            is CreateBookableAction.onLoadMoreMaterial -> loadMoreMaterial()
            is CreateBookableAction.onCreatedBookable -> sendEvent(Destination.Bookable(state.value.createdBookable?.id.toString()))
            is CreateBookableAction.UpdatePhotos -> updateState {
                copy(images = action.images)
            }
        }
    }

    // Function to fetch the list of available materials
    private fun getMaterials() {
        viewModelScope.launch {
            updateState { copy(isLoading = true) }
            try {
                val response = materialRepository.getMaterials(state.value.currentBookableTypeId, state.value.materialPage,10)
                updateState {copy(materials = materials + response.data, totalMaterial = response.totalCount)}
            } catch (e: Exception) {
                Log.e("CreateBookableViewModel", "getMaterials: ${e.message}")
                updateState { copy(error = ErrorMessage.ServerError.message) }
            }
            updateState { copy(isLoading = false) }
        }
    }

    // Function to update the list of selected materials
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

    // Function to load more materials
    private fun loadMoreMaterial() {
        updateState { copy(materialPage = materialPage + 1) }
        getMaterials()
    }
}

/**
 * CreateBookableState is a data class representing the state of the 'CreateBookable' screen.
 *
 * @param bookable: The CreateBookable object that holds bookable item information.
 * @param currentBookableTypeId: The current bookable type ID.
 * @param materialPage: The current page for fetching materials.
 * @param materials: List of available materials.
 * @param totalMaterial: The total count of available materials.
 * @param isLoading: Indicates if data is being loaded.
 * @param error: Error message, if any.
 * @param materialCheckedIds: List of IDs for selected materials.
 * @param createdBookable: The created bookable item, if applicable.
 */
data class CreateBookableState(
    val bookable : CreateBookable = CreateBookable("", "", "", 0, 2),
    val currentBookableTypeId: Int = 2,
    val materialPage: Int = 1,
    val materials: List<Material> = emptyList(),
    val totalMaterial: Int = 0,
    val isLoading: Boolean = true,
    val error: String? = null,
    val materialCheckedIds: List<Int> = emptyList(),
    val createdBookable: Bookable? = null,
    val images: List<Uri> = emptyList()
    )

/**
 * CreateBookableAction is a sealed interface defining various actions that can be performed on the 'CreateBookable' screen.
 */
sealed interface CreateBookableAction {
    object OnCloseClicked : CreateBookableAction
    data class OnNameChanged(val name: String) : CreateBookableAction
    data class OnLocationChanged(val location: String) : CreateBookableAction
    data class OnDescriptionChanged(val description: String) : CreateBookableAction
    data class OnCapacityChanged(val maxCapacity: String) : CreateBookableAction
    data class OnUpdateChecked(val index: Int, val value: Boolean) : CreateBookableAction
    object onLoadMoreMaterial : CreateBookableAction
    object onCreatedBookable : CreateBookableAction
    data class UpdatePhotos(val images: List<Uri>) : CreateBookableAction
}