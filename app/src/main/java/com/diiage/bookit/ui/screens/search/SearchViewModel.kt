package com.diiage.bookit.ui.screens.search

import android.app.Application
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.diiage.bookit.data.remote.ErrorMessage
import com.diiage.bookit.domain.models.Bookable
import com.diiage.bookit.domain.models.Search
import com.diiage.bookit.domain.repositories.SearchRepository
import com.diiage.bookit.ui.core.ViewModel
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.koin.core.component.inject

class SearchViewModel(application: Application) : ViewModel<SearchState>(SearchState(), application) {

    private val searchRepository: SearchRepository by inject()

    fun init(search: String) {
        Log.d("SearchViewModel", "init")
        Log.d("SearchViewModel", search)

        val searchDecoded : Search = Json.decodeFromString(search)

        updateState { copy(search = searchDecoded) }

        getBookables(searchDecoded)
    }

    private fun getBookables(search: Search) {
        fetchData(
            source = { searchRepository.searchBookable(search = search) }
        ) {

            onSuccess {
                updateState { copy(bookables = it.data, totalBookable = it.totalCount, error = null) }
            }

            onFailure {
                updateState { copy(bookables = emptyList(), error = it.toString()) }
            }

            updateState { copy(isLoading = false) }
        }
    }


    fun handleAction(action: SearchAction) {
        when(action) {
            is SearchAction.SelectBookable -> selectBookable(action.bookableId)
        }
    }

    private fun selectBookable(bookableId: Int) {
    }

}

data class SearchState(
    val search: Search = Search(),
    val isLoading: Boolean = true,
    val error: String? = null,
    val bookables: List<Bookable> = emptyList(),
    val totalBookable: Int = 0
)

sealed interface SearchAction {
    data class SelectBookable(val bookableId: Int): SearchAction
}