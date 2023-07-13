package com.msf.plugins

import com.msf.model.Employee
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*

fun Application.configureRequestValidation() {

    install(RequestValidation) {

        validate<Employee> { bodyText ->

            when {
                bodyText.name.isBlank() -> ValidationResult.Invalid("Name field should not be empty")
                (!bodyText.name.matches(Regex("[a-zA-Z]+"))) -> ValidationResult.Invalid("Name should only contain alphabetic characters")
                bodyText.name.length !in 2..50 -> ValidationResult.Invalid("Name should be between 2 and 50 characters")
                bodyText.email.isBlank() -> ValidationResult.Invalid("Email field should not be empty")
                (!bodyText.email.matches(Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}"))) -> ValidationResult.Invalid(
                    "Invalid email address format"
                )

                bodyText.email.length > 100 -> ValidationResult.Invalid("Email should not exceed 100 characters")
                bodyText.id.toInt() <= 0 -> ValidationResult.Invalid("Id should not be 0 or less than 0")
                bodyText.age <= 0 -> ValidationResult.Invalid("age must be positive")
                else -> ValidationResult.Valid

            }
        }
    }
}