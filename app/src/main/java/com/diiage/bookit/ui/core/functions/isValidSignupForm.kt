package com.diiage.bookit.ui.core.functions

import com.diiage.bookit.domain.models.Signup


/*
*  RULES:
*         RuleFor(x => x.Email)
            .NotEmpty()
            .EmailAddress()
            .MaximumLength(200);

        RuleFor(x => x.Password)
            .NotEmpty()
            .MinimumLength(6)
            .Matches("[A-Z]")
            .Matches("[a-z]")
            .Matches("[0-9]"); // Minimum Aa0

        RuleFor(x => x.LastName)
            .NotEmpty()
            .MinimumLength(3)
            .MaximumLength(50);

        RuleFor(x => x.FirstName)
            .NotEmpty()
            .MinimumLength(3)
            .MaximumLength(50);
*
* */
fun isValidSignupForm(signup: Signup): Boolean {
    if (signup.email.isEmpty()
        || !isValidEmail(signup.email)
        || signup.email.length > 200) {
        return false
    }

    if (signup.password.isEmpty()
        || signup.password.length < 6
        || !signup.password.contains(Regex("[A-Z]"))
        || !signup.password.contains(Regex("[a-z]"))
        || !signup.password.contains(Regex("[0-9]"))) {
        return false
    }

    if (signup.lastName.isEmpty()
        || signup.lastName.length < 3
        || signup.lastName.length > 50) {
        return false
    }

    if (signup.firstName.isEmpty()
        || signup.firstName.length < 3
        || signup.firstName.length > 50) {
        return false
    }

    return true
}

