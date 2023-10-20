package com.diiage.bookit.data.remote.repositories

import com.diiage.bookit.data.remote.ApiAuth
import com.diiage.bookit.data.remote.Url
import com.diiage.bookit.data.remote.queries.params.PaginatedParams
import com.diiage.bookit.data.remote.responses.PaginatedResponse
import com.diiage.bookit.domain.models.Material
import com.diiage.bookit.domain.models.Paginated
import com.diiage.bookit.domain.models.Slot
import com.diiage.bookit.domain.repositories.MaterialRepository
import com.diiage.bookit.domain.repositories.SlotRepository

/**
 * This class, MaterialRepositoryImpl, implements the MaterialRepository interface, providing
 * functionality for retrieving a paginated list of materials associated with a specific bookable type.
 *
 * The class uses an instance of the ApiAuth interface to make authenticated requests to the API.
 * It defines the `getMaterials` function, which takes the bookable type ID, page number, and page size
 * as parameters to retrieve materials.
 *
 * @param apiAuth The API authentication interface used for making requests.
 *
 * @see MaterialRepository
 * @see Material
 * @see Paginated
 * @see PaginatedParams
 */
class MaterialRepositoryImpl(
    private val apiAuth: ApiAuth,
) : MaterialRepository {

    override suspend fun getMaterials(
        bookableTypeId: Int,
        page: Int,
        pageSize: Int
    ): Paginated<Material> {
        val paginatedParams = PaginatedParams(page, pageSize)

        val query = paginatedParams.toStringValues()

        val path = Url.Materials.path.replace("{bookableTypeId}", bookableTypeId.toString())

        val response = apiAuth.get<PaginatedResponse<Material>>(path, query)

        return Paginated<Material>(response.data, response.totalCount)
    }
}