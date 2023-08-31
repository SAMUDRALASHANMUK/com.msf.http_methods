package com.msf.plugins

import com.msf.config.status.*
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
                is EmployeeException -> call.respondText(
                    "${cause.message}",
                    status = HttpStatusCode.NotFound
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
                is ArticleCreationException -> call.respondText(
                    " ${cause.message}",
                    status = HttpStatusCode.NotFound
                )

                is ArticleNotFoundException -> call.respondText(
                    " ${cause.message}",
                    status = HttpStatusCode.NotFound
                )

                is CategoryException -> call.respondText(
                    " ${cause.message}",
                    status = HttpStatusCode.NotFound
                )

                is EmployeeException -> call.respondText(
                    " ${cause.message}",
                    status = HttpStatusCode.NotFound
                )

                is PostCategoryException -> call.respondText(
                    " ${cause.message}",
                    status = HttpStatusCode.NotFound
                )

                is PostCategoryCreateException -> call.respondText(
                    " ${cause.message}",
                    status = HttpStatusCode.NotFound
                )

                is PostCreationException -> call.respondText(
                    " ${cause.message}",
                    status = HttpStatusCode.NotFound
                )

                is PostDeleteException -> call.respondText(
                    " ${cause.message}",
                    status = HttpStatusCode.NotFound
                )

                is ProfileCreateException -> call.respondText(
                    " ${cause.message}",
                    status = HttpStatusCode.NotFound
                )

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
