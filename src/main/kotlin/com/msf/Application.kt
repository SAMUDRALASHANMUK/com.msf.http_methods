package com.msf

import com.msf.customexception.EmployeeNotFoundException
import com.msf.model.Employee
import com.msf.routes.empRoutes
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.requestvalidation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    //throwing exceptions
    routing {
        get("/internal-error") {
            throw Exception("Internal Server Error")
        }
    }
    install(ContentNegotiation) {
        json()
    }
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
    install(StatusPages) {
        //Exception Handling using Status Pages
        exception<RequestValidationException> { call, cause ->
            call.respond(status = HttpStatusCode.BadRequest, cause.reasons.joinToString())
        }
        //Default Exception Handler
        exception<Throwable> { call, cause ->
            call.respondText(text = "500: $cause", status = HttpStatusCode.InternalServerError)
        }
        //custom exception
        exception<EmployeeNotFoundException> { call, cause ->
            call.respondText("Employee not found: ${cause.message}", status = HttpStatusCode.BadRequest)
        }
        //The status handler provides the capability to respond with specific content based on the status code.
        status(HttpStatusCode.NotFound) { call, status ->
            call.respondText("404..Page Not Found", status = status)

        }
    }
    empRoutes()
}


