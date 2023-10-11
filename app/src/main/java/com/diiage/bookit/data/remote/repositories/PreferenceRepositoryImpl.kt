package com.diiage.bookit.data.remote.repositories

import android.content.SharedPreferences
import com.diiage.bookit.domain.repositories.PreferenceRepository

class PreferenceRepositoryImpl(private val sharedPreferences: SharedPreferences) : PreferenceRepository {

    private val validKeys: List<String> = listOf("user", "access_token", "refresh_token")

    override fun save(key: String, value: String) {
        if (key !in validKeys) {
            throw IllegalArgumentException("Key not valid")
        }

        sharedPreferences.edit().putString(key, value).apply()
    }

    override fun get(key: String): String? {
        if (key !in validKeys) {
            throw IllegalArgumentException("Key not valid")
        }

        return sharedPreferences.getString(key, null)
    }

    override fun remove(key: String) {
        if (key !in validKeys) {
            throw IllegalArgumentException("Key not valid")
        }

        sharedPreferences.edit().remove(key).apply()
    }
}