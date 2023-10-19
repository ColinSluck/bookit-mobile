package com.diiage.bookit.ui.screens.bookable

import android.app.Application
import android.os.Build
import androidx.lifecycle.viewModelScope
import com.diiage.bookit.data.remote.ErrorMessage
import com.diiage.bookit.domain.models.Material
import com.diiage.bookit.domain.models.Search
import com.diiage.bookit.domain.models.Slot
import com.diiage.bookit.domain.repositories.BookableRepository
import com.diiage.bookit.domain.repositories.SlotRepository
import com.diiage.bookit.ui.core.Destination
import com.diiage.bookit.ui.core.NavigationEvent
import com.diiage.bookit.ui.core.ViewModel
import com.diiage.bookit.ui.screens.login.LoginAction
import kotlinx.coroutines.launch
import org.koin.core.component.inject
import java.time.LocalDate

/**
 * ViewModel responsible for managing the state and actions for the bookable screen.
 *
 * @param application The application instance.
 */
class BookableViewModel(application: Application) : ViewModel<BookableState>(BookableState(), application)
{
    // Injected repositories to interact with bookable data.
    private val bookableRepository : BookableRepository by inject()
    private val slotRepository : SlotRepository by inject()

    /**
     * Initialize data for a specific bookable by its id.
     *
     * @param id The unique identifier for the bookable item.
     */
    fun init(id: Int) {
        getBookabe(id)
    }

    /**
     * Handle different actions performed on the bookable screen.
     *
     * @param action The action to be performed.
     */
    fun handleAction(action: BookableAction) {
        when (action) {
            is BookableAction.SelectDate -> selectDate(action.selectedDate)
            is BookableAction.SelectSlot -> selectSlot(action.slotId)
            is BookableAction.OnBook -> book()
        }
    }

    /**
     * Book a slot for the selected date and bookable.
     */
    fun book() {
        viewModelScope.launch {
            bookableRepository.book(state.value.bookableId, state.value.selectedSlotId, state.value.selectedDate.toString())
            sendEvent(Destination.Bookings)
        }
    }

    /**
     * Select a slot for booking.
     *
     * @param slotId The unique identifier for the slot.
     */
    fun selectSlot(slotId: Int) {
        updateState { copy(selectedSlotId = slotId) }
    }

    /**
     * Select a date for booking and fetch available slots for that date.
     *
     * @param selectedDate The date selected for booking.
     */
    fun selectDate(selectedDate: LocalDate) {
        updateState { copy(selectedDate = selectedDate) }

        viewModelScope.launch{
            val unavailableSlots = bookableRepository.getAvailableSlots(state.value.bookableId, selectedDate.toString())
            val allSlots = slotRepository.getSlots()

            val availableSlots = allSlots.data.filter { slot -> !unavailableSlots.any { it.id == slot.id } }

            updateState { copy(slots = availableSlots) }
            updateState { copy(selectedSlotId = slots.firstOrNull { it.id >= 33 }?.id ?: slots.firstOrNull{ it.id >= 1 }?.id ?: 0 ) }
        }
    }

    /**
     * Fetch and update details for a specific bookable item.
     *
     * @param id The unique identifier for the bookable item.
     */
    fun getBookabe(id: Int){
        viewModelScope.launch {
            try {
                val bookable = bookableRepository.getBookable(id)
                updateState { copy(bookableId = bookable.id) }
                updateState { copy(initialImages = bookable.images) }
                updateState { copy(title = bookable.name) }
                updateState { copy(description = bookable.description) }
                updateState { copy(people = bookable.maxCapacity) }
                updateState { copy(location = bookable.place) }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    selectDate(LocalDate.now())
                }

            }catch (e:Exception){
                updateState { copy(error = ErrorMessage.BookableError.message)}
            }
        }
    }
}

/**
 * Represents the state of the bookable screen.
 *
 * @property initialImages List of image URLs for the bookable.
 * @property title Name of the bookable.
 * @property description Description of the bookable.
 * @property people Maximum capacity of people for the bookable.
 * @property location Location details of the bookable.
 * @property materials List of available materials/equipment for the bookable.
 * @property available Whether the bookable is available for booking.
 * @property bookableId Unique identifier of the bookable.
 * @property slots List of available slots for booking.
 * @property selectedSlotId ID of the selected slot.
 * @property selectedDate Selected date for booking.
 * @property error Error message, if any.
 */
data class BookableState(
    val initialImages: List<String>? = emptyList(),
    val title: String = "Salle de réunion D17",
    val description: String = "La salle de réunion est un espace dédié aux réunions et aux discussions professionnelles. Elle est équipée de tables, de chaises, d'un matériel audiovisuel et d'une machine à café pour faciliter les présentations et offrir un moment de détente aux participants. Cet espace permet aux collaborateurs de se réunir, d'échanger des idées, de prendre des décisions importantes et de profiter d'une pause café. La salle de réunion est un lieu essentiel pour favoriser la collaboration, la productivité et le bien-être au sein de l'entreprise.",
    val people: Int = 6,
    val location: String = "1er étage",
    val materials: List<String> = listOf("Tableau", "Machine à café"),
    val available: Boolean = true,
    val bookableId:Int = -1,

    val slots: List<Slot> = emptyList(),
    val selectedSlotId: Int = 33,
    val selectedDate: LocalDate? = null,
    val error: String? = null
)

/**
 * Represents the different actions that can be performed on the bookable screen.
 */
sealed interface BookableAction {
    data class SelectDate(val selectedDate: LocalDate) : BookableAction

    data class SelectSlot(val slotId: Int) : BookableAction

    object OnBook: BookableAction
}
