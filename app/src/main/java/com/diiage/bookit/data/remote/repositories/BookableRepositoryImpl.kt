package com.diiage.bookit.data.remote.repositories

import com.diiage.bookit.data.remote.ApiAuth
import com.diiage.bookit.data.remote.Url
import com.diiage.bookit.data.remote.mapper.BookableMapper
import com.diiage.bookit.data.remote.queries.params.PaginatedParams
import com.diiage.bookit.data.remote.responses.BookableResponse
import com.diiage.bookit.data.remote.responses.PaginatedResponse
import com.diiage.bookit.domain.models.Bookable
import com.diiage.bookit.domain.models.Paginated
import com.diiage.bookit.domain.models.Search
import com.diiage.bookit.domain.repositories.BookableRepository
import io.ktor.util.*

class BookableRepositoryImpl(
    private val apiAuth: ApiAuth,
    private val mapper: BookableMapper
) : BookableRepository{
    override suspend fun getAvailableBookables(page: Int): Paginated<Bookable> {
        val paginatedParams = PaginatedParams(page, 10)

        val response = apiAuth.get<PaginatedResponse<BookableResponse>>(Url.BookingsAvailable.path, paginatedParams.toStringValues())

        return mapper.toPaginedBookables(response)
    }

    override suspend fun getBookable(id: Int): Bookable {
        val response = apiAuth.get<BookableResponse>(Url.BookingsAvailable.path + "/$id")
        return mapper.toBookable(response)
    }

    override suspend fun createBookable(bookable: Bookable): Bookable {
       val response = apiAuth.post<BookableResponse>(Url.CreateBookable.path, mapper.toCreateBookable(bookable))
        return mapper.toBookable(response)
    }
}