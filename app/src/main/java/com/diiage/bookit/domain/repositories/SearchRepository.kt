package com.diiage.bookit.domain.repositories

import com.diiage.bookit.domain.models.Bookable
import com.diiage.bookit.domain.models.Paginated
import com.diiage.bookit.domain.models.Search

interface SearchRepository {
    suspend fun searchBookable(search: Search): Paginated<Bookable>
}