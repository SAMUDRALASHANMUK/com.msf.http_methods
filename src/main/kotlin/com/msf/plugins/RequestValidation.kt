package com.msf.plugins

import com.msf.model.Employee
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*

fun Application.configureRequestValidation() {

    install(RequestValidation) {

        validate<Employee> { bodyText ->

            if (bodyText.name.isBlank()) {
                ValidationResult.Invalid("Name field should not be empty")
            } else if (!bodyText.name.matches(Regex("[a-zA-Z]+"))) {
                ValidationResult.Invalid("Name should only contain alphabetic characters")
            } else if (bodyText.name.length !in 2..50) {
                ValidationResult.Invalid("Name should be between 2 and 50 characters")
            } else if (bodyText.email.isBlank()) {
                ValidationResult.Invalid("Email field should not be empty")
            } else if (!bodyText.email.matches(Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}"))) {
                ValidationResult.Invalid("Invalid email address format")
            } else if (bodyText.email.length > 100) {
                ValidationResult.Invalid("Email should not exceed 100 characters")
            } else if (bodyText.id <= 0.toString()) {
                ValidationResult.Invalid("Id should not be 0 or less than 0")
            } else if (bodyText.age <= 0) {
                ValidationResult.Invalid("Age should not be 0 or less than 0")
            } else {
                ValidationResult.Valid
            }
        }
    }
}