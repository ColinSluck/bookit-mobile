package com.diiage.bookit.data.remote.repositories

import com.diiage.bookit.data.remote.ApiAuth
import com.diiage.bookit.data.remote.Url
import com.diiage.bookit.data.remote.mapper.BookableMapper
import com.diiage.bookit.data.remote.queries.params.PaginatedParams
import com.diiage.bookit.data.remote.responses.BookableResponse
import com.diiage.bookit.data.remote.responses.PaginatedResponse
import com.diiage.bookit.domain.models.Bookable
import com.diiage.bookit.domain.models.CreateBookable
import com.diiage.bookit.domain.models.Paginated
import com.diiage.bookit.domain.models.Search
import com.diiage.bookit.domain.repositories.BookableRepository
import io.ktor.util.*

class BookableRepositoryImpl(
    private val apiAuth: ApiAuth
) : BookableRepository{
    override suspend fun getAvailableBookables(page: Int): Paginated<Bookable> {
        val paginatedParams = PaginatedParams(page, 10)

        return apiAuth.get<Paginated<Bookable>>(Url.BookingsAvailable.path, paginatedParams.toStringValues())
    }

    override suspend fun getBookable(id: Int): Bookable {
        return apiAuth.get<Bookable>(Url.BookingsAvailable.path + "/$id")
    }

    override suspend fun createBookable(bookable: CreateBookable): Bookable {
       return apiAuth.post<Bookable>(Url.CreateBookable.path, bookable)
    }
}