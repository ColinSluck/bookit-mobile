package com.diiage.bookit.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Paginated<T> (
    var data: List<T>,
    var totalCount: Int
)