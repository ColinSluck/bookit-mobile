package com.diiage.bookit.data.remote.repositories

import com.diiage.bookit.data.remote.ApiAuth
import com.diiage.bookit.data.remote.Url
import com.diiage.bookit.data.remote.queries.params.PaginatedParams
import com.diiage.bookit.data.remote.responses.PaginatedResponse
import com.diiage.bookit.domain.models.Booking
import com.diiage.bookit.domain.models.Paginated
import com.diiage.bookit.domain.repositories.BookingRepository

class BookingRepositoryImpl(private val apiAuth: ApiAuth) : BookingRepository {
    override suspend fun getBookings(page: Int, pageSize: Int): Paginated<Booking> {
        val query = PaginatedParams(page, pageSize)

        val response = apiAuth.get<PaginatedResponse<Booking>>(Url.Bookings.path, query.toStringValues())

        return Paginated(response.data, response.totalCount)
    }

}