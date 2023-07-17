package com.msf.plugins

import com.msf.domain.exceptions.EmployeeNotFoundException
import com.msf.domain.exceptions.PostNotFoundException
import com.msf.domain.exceptions.ProfileNotFoundException
import com.msf.domain.exceptions.UserNotFoundException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*

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
                    status = HttpStatusCode.NotFound // Change this to NotFound
                )

                else -> call.respondText(
                    text = "$cause",
                    status = HttpStatusCode.InternalServerError
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
