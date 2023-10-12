package com.diiage.bookit.ui.core.functions

import com.diiage.bookit.domain.models.Credentials

fun isValidLoginForm(credentials: Credentials): Boolean {
    return credentials.email.isNotEmpty() && isValidEmail(credentials.email) && credentials.password.isNotEmpty()
}

