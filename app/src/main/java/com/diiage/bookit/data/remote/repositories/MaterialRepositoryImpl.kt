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