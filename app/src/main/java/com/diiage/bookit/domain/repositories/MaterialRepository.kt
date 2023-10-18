package com.diiage.bookit.domain.repositories

import com.diiage.bookit.domain.models.Material
import com.diiage.bookit.domain.models.Paginated
import com.diiage.bookit.domain.models.Slot

interface MaterialRepository {
    suspend fun getMaterials(bookableTypeId: Int, page: Int, pageSize: Int): Paginated<Material>
}