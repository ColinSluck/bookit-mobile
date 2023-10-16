package com.diiage.bookit.domain.repositories

import com.diiage.bookit.domain.models.Paginated
import com.diiage.bookit.domain.models.Slot

interface SlotRepository {
    suspend fun getSlots(): Paginated<Slot>
}