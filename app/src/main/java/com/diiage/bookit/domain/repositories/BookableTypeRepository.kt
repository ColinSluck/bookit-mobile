package com.diiage.bookit.domain.repositories

interface BookableTypeRepository {
    fun getBookableType(Id: Int)

    suspend fun postBookableType(Libelle:String)

    fun deleteBookableType(Id:Int)
}