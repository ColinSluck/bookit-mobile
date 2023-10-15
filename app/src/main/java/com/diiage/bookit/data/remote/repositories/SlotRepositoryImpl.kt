package com.diiage.bookit.data.remote.repositories

import com.diiage.bookit.data.remote.ApiAuth
import com.diiage.bookit.data.remote.Url
import com.diiage.bookit.data.remote.queries.params.PaginatedParams
import com.diiage.bookit.data.remote.responses.PaginatedResponse
import com.diiage.bookit.domain.models.Paginated
import com.diiage.bookit.domain.models.Slot
import com.diiage.bookit.domain.repositories.SlotRepository

class SlotRepositoryImpl(
    private val apiAuth: ApiAuth,
) : SlotRepository {
    override suspend fun getSlots(): Paginated<Slot> {
        val paginatedParams = PaginatedParams(1, 96)

        val query = paginatedParams.toStringValues()

        val response = apiAuth.get<PaginatedResponse<Slot>>(Url.Slots.path, query)

        // startTime & EndTime are in format "15:30:00" -> we need to convert them to "15:30"
        return Paginated<Slot>(response.data.map {
            Slot(it.id, it.startTime.substring(0, 5), it.endTime.substring(0, 5))
        }, response.totalCount)
    }
}