package com.diiage.bookit.data.remote.repositories

import android.content.Context
import com.diiage.bookit.domain.repositories.PreferenceRepository

class PreferenceRepositoryImpl(private val context: Context) : PreferenceRepository {

    private val PREF_NAME = "com.bookit.preferences"

    private val preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    private val validKeys: List<String> = listOf("user", "access_token", "refresh_token")

    override fun save(key: String, value: String) {
        if (key !in validKeys) throw IllegalArgumentException("$key n'est pas une clé valide")
        val editor = preferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    override fun get(key: String): String? {
        if (key !in validKeys) throw IllegalArgumentException("$key n'est pas une clé valide")
        return preferences.getString(key, null)
    }

    override fun remove(key: String) {
        if (key !in validKeys) throw IllegalArgumentException("$key n'est pas une clé valide")
        val editor = preferences.edit()
        editor.remove(key)
        editor.apply()
    }
}