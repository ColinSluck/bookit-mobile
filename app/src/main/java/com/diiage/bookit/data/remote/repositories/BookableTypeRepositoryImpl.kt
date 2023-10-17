package com.diiage.bookit.data.remote.repositories

import com.diiage.bookit.data.remote.response.BookableType
import com.diiage.bookit.domain.repositories.BookableTypeRepository

internal class BookableTypeRepositoryImpl(
    private val bookableType : BookableType

):BookableTypeRepository {
    override fun getBookableType(Id: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun postBookableType(
        Libelle: String
    ) {
        TODO("Not yet implemented")
    }

    override fun deleteBookableType(Id: Int) {
        TODO("Not yet implemented")
    }


}