package com.diiage.bookit.data.remote.repositories

import com.diiage.bookit.data.remote.ApiAuth
import com.diiage.bookit.data.remote.Url
import com.diiage.bookit.data.remote.queries.params.PaginatedParams
import com.diiage.bookit.domain.models.Bookable
import com.diiage.bookit.domain.models.CreateBookable
import com.diiage.bookit.domain.models.Paginated
import com.diiage.bookit.domain.repositories.BookableRepository

/**
 * The BookableRepositoryImpl class implements the BookableRepository interface to provide
 * functionality for interacting with the remote data source to manage bookable items.
 *
 * @param apiAuth: The API client for making authenticated requests to the remote server.
 */
class BookableRepositoryImpl(
    private val apiAuth: ApiAuth
) : BookableRepository{
    /**
     * Fetch a paginated list of available bookable items from the remote server.
     *
     * @param page: The page number to retrieve.
     * @return A paginated list of available bookable items.
     */
    override suspend fun getAvailableBookables(page: Int): Paginated<Bookable> {
        val paginatedParams = PaginatedParams(page, 10)

        return apiAuth.get<Paginated<Bookable>>(Url.BookingsAvailable.path, paginatedParams.toStringValues())
    }

    /**
     * Retrieve a specific bookable item by its ID from the remote server.
     *
     * @param id: The unique identifier of the bookable item.
     * @return The bookable item with the specified ID.
     */
    override suspend fun getBookable(id: Int): Bookable {
        return apiAuth.get<Bookable>(Url.Bookable.path.replace("{id}",id.toString()))
    }

    /**
     * Create a new bookable item with the provided details on the remote server.
     *
     * @param bookable: The CreateBookable object containing information about the new bookable item.
     * @return The created bookable item.
     */
    override suspend fun createBookable(bookable: CreateBookable): Bookable {
       return apiAuth.post<Bookable>(Url.CreateBookable.path, bookable)
    }
}