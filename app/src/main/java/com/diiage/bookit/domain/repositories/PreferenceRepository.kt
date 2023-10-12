package com.diiage.bookit.domain.repositories

interface PreferenceRepository {
    fun save(key: String, value: String)
    fun get(key: String): String?
    fun remove(key: String)
}