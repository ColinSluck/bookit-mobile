package com.diiage.bookit.domain.repositories

import com.diiage.bookit.domain.models.Paginated
import com.diiage.bookit.domain.models.Slot
/**
 * This interface, SlotRepository, defines the contract for retrieving a paginated list of time slots
 * that are available for bookings.
 *
 * Implementing classes or interfaces must provide a function for fetching time slots.
 *
 * @see Slot
 * @see Paginated
 */
interface SlotRepository {
    suspend fun getSlots(): Paginated<Slot>
}