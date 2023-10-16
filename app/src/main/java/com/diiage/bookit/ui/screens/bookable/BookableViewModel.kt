package com.diiage.bookit.ui.screens.bookable

import android.app.Application
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import com.diiage.bookit.R
import com.diiage.bookit.domain.models.Bookable
import com.diiage.bookit.domain.repositories.BookableRepository
import com.diiage.bookit.ui.core.ViewModel
import org.koin.core.component.inject

class BookableViewModel(application: Application) : ViewModel<BookableState>(BookableState(), application){
    private val bookableRepository: BookableRepository by inject()

    fun init(id: Int) {
        fetchData(
            source = { bookableRepository.getBookable(id) }
        ) {

            onSuccess {
                updateState { copy(bookable = it, error = null) }
            }

            onFailure {
                updateState { copy(error = it.toString()) }
            }

            updateState { copy(isLoading = false) }
        }
    }
}

data class BookableState(
    val bookable: Bookable = Bookable(0, "", "", "", "", "", -1, -1),
    val error: String? = null,
    val isLoading: Boolean = true,
)