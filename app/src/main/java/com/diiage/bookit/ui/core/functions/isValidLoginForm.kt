package com.diiage.bookit.ui.core.functions

import com.diiage.bookit.domain.models.Credentials

/**
 * Checks the validity of the provided [credentials] for the login form.
 *
 * - Validates if the email is not empty and is in a valid format.
 * - Validates if the password is not empty.
 *
 * @param credentials The user's login information.
 * @return true if the credentials are valid, false otherwise.
 */
fun isValidLoginForm(credentials: Credentials): Boolean {
    return credentials.email.isNotEmpty() && isValidEmail(credentials.email) && credentials.password.isNotEmpty()
}

