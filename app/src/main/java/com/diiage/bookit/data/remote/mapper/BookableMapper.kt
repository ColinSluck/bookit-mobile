package com.diiage.bookit.data.remote.mapper

import com.diiage.bookit.data.remote.requests.CreateBookable
import com.diiage.bookit.data.remote.responses.BookableResponse
import com.diiage.bookit.data.remote.responses.PaginatedResponse
import com.diiage.bookit.domain.models.Bookable
import com.diiage.bookit.domain.models.Paginated
import org.mapstruct.Mapper

@Mapper
interface BookableMapper {
    fun toBookable(bookableResponse: BookableResponse): Bookable
    fun toPaginedBookables(bookableResponses: PaginatedResponse<BookableResponse>): Paginated<Bookable>
    fun toCreateBookable(bookable: Bookable): CreateBookable
}