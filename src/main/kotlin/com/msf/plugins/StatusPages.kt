package com.msf.plugins

import com.msf.domain.exceptions.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import org.jetbrains.exposed.exceptions.ExposedSQLException

fun Application.configureStatusPages() {
    install(StatusPages) {

        exception<Throwable> { call, cause ->
            when (cause) {
                is EmployeeNotFoundException -> call.respondText(
                    "Employee not found: ${cause.message}",
                    status = HttpStatusCode.BadRequest
                )

                is PostNotFoundException -> call.respondText(
                    "Post not found: ${cause.message}",
                    status = HttpStatusCode.BadRequest
                )

                is ProfileNotFoundException -> call.respondText(
                    "Profile not found: ${cause.message}",
                    status = HttpStatusCode.BadRequest
                )

                is UserNotFoundException -> call.respondText(
                    "User not found: ${cause.message}",
                    status = HttpStatusCode.NotFound
                )

                is ExposedSQLException -> call.respondText(
                    "Data Base Error: ${cause.message}",
                    status = HttpStatusCode.BadRequest
                )

                is IllegalStateException -> call.respond(HttpStatusCode.BadRequest, "Bad request: ${cause.message}")

                is UserDeletionException -> call.respond(HttpStatusCode.NotFound, message = " ${cause.message}")
                else -> call.respondText(
                    text = "${cause.message}",
                )
            }
        }

        status(HttpStatusCode.NotFound) { call, status ->
            call.respondText(
                "Oops! It seems the page you're looking for cannot be found. Please check the URL or try navigating back to the previous page",
                status = status
            )
        }
    }
}
