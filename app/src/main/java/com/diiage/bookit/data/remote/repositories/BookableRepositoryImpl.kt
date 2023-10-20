package com.diiage.bookit.data.remote.repositories

import android.net.Uri
import com.diiage.bookit.data.remote.ApiAuth
import com.diiage.bookit.data.remote.Url
import com.diiage.bookit.data.remote.queries.params.PaginatedParams
import com.diiage.bookit.domain.models.Bookable
import com.diiage.bookit.domain.models.CreateBookable
import com.diiage.bookit.domain.models.Image
import com.diiage.bookit.domain.models.Paginated
import com.diiage.bookit.domain.models.UploadableFile
import com.diiage.bookit.data.remote.requests.BookBookable
import com.diiage.bookit.data.remote.response.BookableSlots
import com.diiage.bookit.domain.models.*
import com.diiage.bookit.domain.repositories.BookableRepository

/**
 * A remote implementation of the [BookableRepository], utilizing the [ApiAuth] to make requests to the backend API.
 *
 * @property apiAuth The authentication API client.
 */
class BookableRepositoryImpl(
    private val apiAuth: ApiAuth
) : BookableRepository {

    /**
     * Fetches available bookables with pagination from the remote backend.
     *
     * @param page The page number to fetch.
     * @return A paginated list of available bookables.
     */
    override suspend fun getAvailableBookables(page: Int): Paginated<Bookable> {
        val paginatedParams = PaginatedParams(page, 10)
        return apiAuth.get<Paginated<Bookable>>(Url.BookingsAvailable.path, paginatedParams.toStringValues())
    }

    /**
     * Retrieves a specific bookable's details from the remote backend based on its ID.
     *
     * @param id The unique identifier of the bookable.
     * @return The details of the bookable.
     */
    override suspend fun getBookable(id: Int): Bookable {
        return apiAuth.get<Bookable>(Url.Bookable.path.replace("{id}",id.toString()))
    }

    /**
     * Sends a request to create a new bookable entry in the remote backend.
     *
     * @param bookable An instance containing the details of the bookable to be created.
     * @return The created bookable details.
     */
    override suspend fun createBookable(bookable: CreateBookable): Bookable {
        return apiAuth.post<Bookable>(Url.CreateBookable.path, bookable)
    }

    /**
     * Fetches available slots for a specific bookable from the remote backend on a given date.
     *
     * @param bookableId The unique identifier of the bookable.
     * @param date The date for which slots need to be fetched.
     * @return A list of available slots.
     */
    override suspend fun getAvailableSlots(bookableId: Int, date: String): List<Slot> {
        val path = Url.BookableSlots.path.replace("{id}", bookableId.toString()) + "?date=${date}&offset=0&limit=96"
        val response = apiAuth.get<BookableSlots>(path)
        return response.data
    }

    /**
     * Sends a request to book a specific slot for a bookable in the remote backend on a given date.
     *
     * @param bookableId The unique identifier of the bookable.
     * @param slotId The ID of the slot to be booked.
     * @param date The date for which the slot is being booked.
     * @return The booking confirmation details.
     */
    override suspend fun book(bookableId: Int, slotId: Int, date: String): Book {
        val book = BookBookable(date, slotId)
        return apiAuth.post<Book>(Url.BookBookable.path.replace("{id}", bookableId.toString()), book)
    }

    override suspend fun uploadImages(bookableId: Int, uploadableFiles: List<UploadableFile>): List<Image> {
        val path = Url.UploadImage.path.replace("{id}", bookableId.toString())

        return apiAuth.postWithFiles<List<Image>>(path, uploadableFiles)
    }
}