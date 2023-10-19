package com.diiage.bookit.domain.models

import kotlinx.serialization.Serializable

/**
 * Represents user credentials.
 *
 * @property email The user's email address.
 * @property password The user's password.
 */
@Serializable
class Credentials (val email: String, val password: String) {
}