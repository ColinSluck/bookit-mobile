package com.diiage.bookit.domain.repositories

import com.diiage.bookit.domain.models.*

/**
 * The BookableRepository interface defines the contract for interacting with the repository
 * responsible for managing bookable items in the application.
 */
interface BookableRepository {

    /**
     * Fetches available bookables with pagination.
     *
     * @param page The page number to fetch.
     * @return A paginated list of available bookables.
     */
    suspend fun getAvailableBookables(page: Int): Paginated<Bookable>

    /**
     * Fetches the details of a specific bookable based on its ID.
     *
     * @param id The unique identifier of the bookable.
     * @return The details of the bookable.
     */
    suspend fun getBookable(id: Int): Bookable

    /**
     * Creates a new bookable entry.
     *
     * @param bookable An instance containing the details of the bookable to be created.
     * @return The created bookable details.
     */
    suspend fun createBookable(bookable: CreateBookable): Bookable

    /**
     * Fetches available slots for a specific bookable on a given date.
     *
     * @param bookableId The unique identifier of the bookable.
     * @param date The date for which slots need to be fetched.
     * @return A list of available slots.
     */
    suspend fun getAvailableSlots(bookableId: Int, date: String): List<Slot>

    /**
     * Books a specific slot for a bookable on a given date.
     *
     * @param bookableId The unique identifier of the bookable.
     * @param slotId The ID of the slot to be booked.
     * @param date The date for which the slot is being booked.
     * @return The booking confirmation details.
     */
    suspend fun book(bookableId: Int, slotId: Int, date: String): Book
}