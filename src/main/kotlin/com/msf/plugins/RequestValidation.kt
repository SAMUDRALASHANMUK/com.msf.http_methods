package com.msf.plugins

import com.msf.data.model.Employee
import com.msf.data.model.User
import com.msf.util.appconstants.GlobalConstants.MAX_EMAIL_LENGTH
import com.msf.util.appconstants.GlobalConstants.MAX_USER_NAME_LENGTH
import com.msf.util.appconstants.GlobalConstants.MIN_USER_NAME_LENGTH
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.requestvalidation.RequestValidation
import io.ktor.server.plugins.requestvalidation.ValidationResult

fun Application.configureRequestValidation() {
    install(RequestValidation) {
        validate<Employee> { bodyText ->
            when (val validationResult = validateEmployee(bodyText)) {
                is ValidationResult.Invalid -> validationResult
                else -> ValidationResult.Valid
            }
        }

        validate<User> { bodyText ->
            when (val validationResult = validateUser(bodyText)) {
                is ValidationResult.Invalid -> validationResult
                else -> ValidationResult.Valid
            }
        }
    }
}

private fun validateEmployee(employee: Employee): ValidationResult {
    with(employee) {
        return when {
            name.isBlank() -> ValidationResult.Invalid("Name field should not be empty")
            !name.matches(Regex("[a-zA-Z]+")) ->
                ValidationResult.Invalid("Name should only contain alphabetic characters")

            name.length !in MIN_USER_NAME_LENGTH..MAX_USER_NAME_LENGTH ->
                ValidationResult.Invalid("Name should be between 2 and 50 characters")

            email.isBlank() -> ValidationResult.Invalid("Email field should not be empty")
            !email.matches(Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")) ->
                ValidationResult.Invalid("Invalid email address format")

            email.length > MAX_EMAIL_LENGTH ->
                ValidationResult.Invalid("Email should not exceed 100 characters")

            id <= 0 -> ValidationResult.Invalid("Id should not be 0 or less than 0")
            age <= 0 -> ValidationResult.Invalid("Age must be positive")
            else -> ValidationResult.Valid
        }
    }
}

private fun validateUser(user: User): ValidationResult {
    with(user) {
        return when {
            userName.isBlank() -> ValidationResult.Invalid("User name field should not be empty")
            !userName.matches(Regex("[a-zA-Z]+")) ->
                ValidationResult.Invalid("User name should only contain alphabetic characters")

            userName.length !in MIN_USER_NAME_LENGTH..MAX_USER_NAME_LENGTH ->
                ValidationResult.Invalid("User name should be between 2 and 50 characters")

            email.isBlank() -> ValidationResult.Invalid("Email field should not be empty")
            !email.matches(Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")) ->
                ValidationResult.Invalid("Invalid email address format")

            email.length > MAX_EMAIL_LENGTH ->
                ValidationResult.Invalid("Email should not exceed 100 characters")

            else -> ValidationResult.Valid
        }
    }
}
