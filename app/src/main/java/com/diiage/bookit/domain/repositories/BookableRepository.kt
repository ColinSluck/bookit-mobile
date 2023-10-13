package com.diiage.bookit.domain.repositories

import com.diiage.bookit.domain.models.Bookable
import com.diiage.bookit.domain.models.Paginated

interface BookableRepository {
    suspend fun getAvailableBookables(page: Int): Paginated<Bookable>

    suspend fun getBookable(id: Int): Bookable

    fun createBookable(bookable: Bookable): Bookable
}