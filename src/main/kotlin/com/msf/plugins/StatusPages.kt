package com.msf.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.requestvalidation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

fun Application.configureStatusPages() {
    install(StatusPages) {
        //Exception Handling using Status Pages
        exception<RequestValidationException> { call, cause ->
            call.respond(status = HttpStatusCode.BadRequest, cause.reasons.joinToString())
        }
        //Default Exception Handler
        exception<Throwable> { call, cause ->
            call.respondText(text = "An internal server error occurred : $cause", status = HttpStatusCode.InternalServerError)
        }
        //custom exception
        exception<EmployeeNotFoundException> { call, cause ->
            call.respondText(" ${cause.message}", status = HttpStatusCode.BadRequest)
        }
        //The status handler provides the capability to respond with specific content based on the status code.
        status(HttpStatusCode.NotFound) { call, status ->
            call.respondText("Oops! It seems the page you're looking for cannot be found Please check the URL or try navigating back to the previous page", status = status)

        }
    }
}

class EmployeeNotFoundException(message: String) : Exception(message)