package com.diiage.bookit.domain.repositories

import com.diiage.bookit.domain.models.Bookable
import com.diiage.bookit.domain.models.Paginated
import com.diiage.bookit.domain.models.Search

/**
 * This interface, SearchRepository, defines the contract for searching bookable items within a repository.
 * Implementing classes or interfaces must provide a method for searching bookable items based on a `Search` input.
 *
 * @see Search
 * @see Bookable
 * @see Paginated
 */
interface SearchRepository {
    suspend fun searchBookable(search: Search): Paginated<Bookable>
}