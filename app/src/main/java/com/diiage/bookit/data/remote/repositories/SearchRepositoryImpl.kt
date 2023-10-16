package com.diiage.bookit.data.remote.repositories

import com.diiage.bookit.data.remote.ApiAuth
import com.diiage.bookit.data.remote.Url
import com.diiage.bookit.data.remote.responses.BookableResponse
import com.diiage.bookit.data.remote.responses.PaginatedResponse
import com.diiage.bookit.domain.models.Bookable
import com.diiage.bookit.domain.models.Paginated
import com.diiage.bookit.domain.models.Search
import com.diiage.bookit.domain.repositories.SearchRepository

class SearchRepositoryImpl(
    private val apiAuth: ApiAuth,
) : SearchRepository {
    override suspend fun searchBookable(search: Search): Paginated<Bookable> {
        val query = search.toStringValues()
        val response = apiAuth.get<PaginatedResponse<Bookable>>(Url.SearchBookable.path, query)
        return Paginated(response.data, response.totalCount)
    }
}