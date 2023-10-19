package com.diiage.bookit.domain.repositories

import com.diiage.bookit.domain.models.Material
import com.diiage.bookit.domain.models.Paginated
import com.diiage.bookit.domain.models.Slot
/**
 * This interface, MaterialRepository, defines the contract for retrieving a paginated list of materials
 * associated with a specific bookable type.
 *
 * Implementing classes or interfaces must provide a function for fetching materials based on the bookable type ID,
 * page number, and page size.
 *
 * @see Material
 * @see Paginated
 */
interface MaterialRepository {
    suspend fun getMaterials(bookableTypeId: Int, page: Int, pageSize: Int): Paginated<Material>
}