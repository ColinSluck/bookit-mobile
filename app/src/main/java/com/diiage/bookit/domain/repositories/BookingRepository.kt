package com.diiage.bookit.domain.repositories

import com.diiage.bookit.domain.models.Booking
import com.diiage.bookit.domain.models.Paginated

interface BookingRepository {
    suspend fun getBookings(page: Int, pageSize: Int) : Paginated<Booking>
}