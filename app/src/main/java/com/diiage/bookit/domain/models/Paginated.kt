package com.diiage.bookit.domain.models

data class Paginated<T> (
    var data: List<T>,
    var totalCount: Int
)