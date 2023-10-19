package com.diiage.bookit.domain.repositories

import com.diiage.bookit.domain.models.Bookable
import com.diiage.bookit.domain.models.CreateBookable
import com.diiage.bookit.domain.models.Paginated
import com.diiage.bookit.domain.models.Search

/**
 * The BookableRepository interface defines the contract for interacting with the repository
 * responsible for managing bookable items in the application.
 */
interface BookableRepository {
    /**
     * Fetch a paginated list of available bookable items.
     *
     * @param page: The page number to retrieve.
     * @return A paginated list of available bookable items.
     */
    suspend fun getAvailableBookables(page: Int): Paginated<Bookable>

    /**
     * Retrieve a specific bookable item by its ID.
     *
     * @param id: The unique identifier of the bookable item.
     * @return The bookable item with the specified ID.
     */
    suspend fun getBookable(id: Int): Bookable

    /**
     * Create a new bookable item with the provided details.
     *
     * @param bookable: The CreateBookable object containing information about the new bookable item.
     * @return The created bookable item.
     */
    suspend fun createBookable(bookable: CreateBookable): Bookable
}