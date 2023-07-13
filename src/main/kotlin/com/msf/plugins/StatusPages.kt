package com.msf.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureStatusPages() {
    install(StatusPages) {

        exception<Throwable> { call, cause ->
            when (cause) {
                is RequestValidationException -> call.respondText(
                    text = cause.reasons.joinToString(),
                    status = HttpStatusCode.BadRequest
                )

                is EmployeeNotFoundException -> call.respondText(
                    "Employee not found: ${cause.message}",
                    status = HttpStatusCode.BadRequest
                )

                else -> call.respondText(
                    text = "$cause",
                    status = HttpStatusCode.InternalServerError
                )
            }
        }

        //The status handler provides the capability to respond with specific content based on the status code.
        status(HttpStatusCode.NotFound) { call, status ->
            call.respondText(
                "Oops! It seems the page you're looking for cannot be found Please check the URL or try navigating back to the previous page",
                status = status
            )

        }
    }
}

class EmployeeNotFoundException(message: String = "Employee not found") : Exception(message)

