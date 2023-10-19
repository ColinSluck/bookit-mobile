package com.diiage.bookit.data.remote.repositories

import com.diiage.bookit.data.remote.ApiAuth
import com.diiage.bookit.data.remote.Url
import com.diiage.bookit.data.remote.responses.BookableResponse
import com.diiage.bookit.data.remote.responses.PaginatedResponse
import com.diiage.bookit.domain.models.Bookable
import com.diiage.bookit.domain.models.Paginated
import com.diiage.bookit.domain.models.Search
import com.diiage.bookit.domain.repositories.SearchRepository

/**
 * This class, SearchRepositoryImpl, implements the SearchRepository interface, providing
 * functionality for searching bookable items through an external API.
 *
 * The class uses an instance of the ApiAuth interface to perform authenticated requests.
 * It defines the `searchBookable` function, which takes a `Search` object, converts it into query parameters,
 * and makes a network request to retrieve a paginated list of bookable items.
 *
 * @param apiAuth The API authentication interface used for making requests.
 *
 * @see SearchRepository
 * @see Search
 * @see Bookable
 * @see Paginated
 */
class SearchRepositoryImpl(
    private val apiAuth: ApiAuth,
) : SearchRepository {
    override suspend fun searchBookable(search: Search): Paginated<Bookable> {
        val query = search.toStringValues()
        val response = apiAuth.get<PaginatedResponse<Bookable>>(Url.SearchBookable.path, query)
        return Paginated(response.data, response.totalCount)
    }
}