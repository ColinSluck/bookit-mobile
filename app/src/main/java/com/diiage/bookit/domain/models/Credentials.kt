package com.diiage.bookit.domain.models

import kotlinx.serialization.Serializable

@Serializable
class Credentials (val email: String, val password: String) {
}