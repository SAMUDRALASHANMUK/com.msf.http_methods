package com.msf.plugins

import com.msf.domain.exceptions.EmployeeNotFoundException
import com.msf.domain.exceptions.PostNotFoundException
import com.msf.domain.exceptions.ProfileNotFoundException
import com.msf.domain.exceptions.UserNotFoundException
import com.msf.domain.exceptions.UserDeletionException
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respondText
import io.ktor.server.response.respond
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
                "Oops! It seems the page you're looking for cannot be found.",
                status = status
            )
        }
    }
}
