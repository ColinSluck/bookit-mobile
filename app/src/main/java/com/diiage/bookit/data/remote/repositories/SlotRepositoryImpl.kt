package com.diiage.bookit.data.remote.repositories

import com.diiage.bookit.data.remote.ApiAuth
import com.diiage.bookit.data.remote.Url
import com.diiage.bookit.data.remote.queries.params.PaginatedParams
import com.diiage.bookit.data.remote.responses.PaginatedResponse
import com.diiage.bookit.domain.models.Paginated
import com.diiage.bookit.domain.models.Slot
import com.diiage.bookit.domain.repositories.SlotRepository
/**
 * This class, SlotRepositoryImpl, implements the SlotRepository interface, providing
 * functionality for retrieving a paginated list of time slots available for bookings.
 *
 * The class uses an instance of the ApiAuth interface to make authenticated requests to the API.
 * It defines the `getSlots` function, which retrieves time slots and converts the start and end times
 * to a more readable format.
 *
 * @param apiAuth The API authentication interface used for making requests.
 *
 * @see SlotRepository
 * @see Slot
 * @see Paginated
 * @see PaginatedParams
 */
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