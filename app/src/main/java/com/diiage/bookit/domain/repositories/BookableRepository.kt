package com.diiage.bookit.domain.repositories

import com.diiage.bookit.domain.models.Bookable
import com.diiage.bookit.domain.models.Paginated
import com.diiage.bookit.domain.models.Search

interface BookableRepository {
    suspend fun getAvailableBookables(page: Int): Paginated<Bookable>

    suspend fun getBookable(id: Int): Bookable

    suspend fun createBookable(bookable: Bookable): Bookable
}