package com.diiage.bookit.ui.screens.search

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.diiage.bookit.domain.models.Bookable
import com.diiage.bookit.domain.models.Search
import com.diiage.bookit.domain.models.User
import com.diiage.bookit.domain.repositories.PreferenceRepository
import com.diiage.bookit.domain.repositories.SearchRepository
import com.diiage.bookit.ui.core.Destination
import com.diiage.bookit.ui.core.ViewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.koin.core.component.inject

class SearchViewModel(application: Application) : ViewModel<SearchState>(SearchState(), application) {

    private val searchRepository: SearchRepository by inject()
    private val preferencesRepository: PreferenceRepository by inject()

    fun init(search: String) {
        Log.d("SearchViewModel", "SearchViewModel: init $search")

        val searchDecoded : Search = Json.decodeFromString(search)

        updateState { copy(search = searchDecoded.copy(date = null, slotId = null)) }

        getFirstName()
        getBookables(state.value.search)
    }

    private fun getBookables(search: Search) {
        viewModelScope.launch {
            updateState { copy(isLoading = true) }
            try {
                val response = searchRepository.searchBookable(search)
                updateState {copy(bookables = bookables + response.data, totalBookable = response.totalCount)}
            } catch (e: Exception) {
                updateState { copy(error = e.message) }
            }
            updateState { copy(isLoading = false) }
        }
    }

    private fun getFirstName() {
        val userString = preferencesRepository.get("user")

        if(userString != null) {
            val user: User = Json.decodeFromString(userString)

            updateState { copy(firstName = user.firstName) }
        }
    }

    fun handleAction(action: SearchAction) {
        when(action) {
            is SearchAction.SelectBookable -> selectBookable(action.bookableId)
            is SearchAction.LoadMoreBookables -> loadMoreBookables()
        }
    }

    private fun loadMoreBookables() {
        updateState {
            copy(search = search.copy(page = search.page + 1))
        }
        getBookables(state.value.search)
    }

    private fun selectBookable(bookableId: Int) {
        sendEvent(Destination.Bookable(bookableId.toString()))
    }

}

data class SearchState(
    val search: Search = Search(),
    val isLoading: Boolean = true,
    val error: String? = null,
    val bookables: List<Bookable> = emptyList(),
    val totalBookable: Int = 0,
    val firstName: String = ""
)

sealed interface SearchAction {
    data class SelectBookable(val bookableId: Int): SearchAction
    object LoadMoreBookables: SearchAction
}