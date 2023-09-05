package com.msf.plugins

import com.msf.exception.*
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

                is ExposedSQLException -> call.respondText(
                    "Data Base Error: ${cause.message}",
                    status = HttpStatusCode.BadRequest
                )

                is IllegalStateException -> call.respond(HttpStatusCode.BadRequest, "Bad request: ${cause.message}")

                is ArticleCreateException -> call.respondText(
                    " ${cause.message}",
                    status = HttpStatusCode.NotFound
                )

                is ArticleNotFoundException -> call.respondText(
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

                is CategoryCreateException -> call.respondText(
                    " ${cause.message}",
                    status = HttpStatusCode.NotFound
                )

                is CategoryUpdateException -> call.respondText(
                    " ${cause.message}",
                    status = HttpStatusCode.NotFound
                )

                is CategoryDeleteException -> call.respondText(
                    " ${cause.message}",
                    status = HttpStatusCode.NotFound
                )

                is CategoryNotFoundException -> call.respondText(
                    "${cause.message}",
                    status = HttpStatusCode.BadRequest
                )

                is ProfileCreateException -> call.respondText(
                    " ${cause.message}",
                    status = HttpStatusCode.NotFound
                )

                is ProfileUpdateException -> call.respondText(
                    " ${cause.message}",
                    status = HttpStatusCode.NotFound
                )

                is ProfileDeleteException -> call.respondText(
                    " ${cause.message}",
                    status = HttpStatusCode.NotFound
                )

                is ProfileNotFoundException -> call.respondText(
                    "${cause.message}",
                    status = HttpStatusCode.BadRequest
                )

                is UserCreateException -> call.respondText(
                    "${cause.message}",
                    status = HttpStatusCode.NotFound
                )

                is UserUpdateException -> call.respondText(
                    "${cause.message}",
                    status = HttpStatusCode.NotFound
                )

                is UserDeleteException -> call.respondText(
                    "${cause.message}",
                    status = HttpStatusCode.NotFound
                )

                is UserNotFoundException -> call.respondText(
                    "${cause.message}",
                    status = HttpStatusCode.NotFound
                )

                is PostCreateException -> call.respondText(
                    "${cause.message}",
                    status = HttpStatusCode.BadRequest
                )

                is PostUpdateException -> call.respondText(
                    "${cause.message}",
                    status = HttpStatusCode.BadRequest
                )

                is PostDeleteException -> call.respondText(
                    "${cause.message}",
                    status = HttpStatusCode.BadRequest
                )

                is PostNotFoundException -> call.respondText(
                    "${cause.message}",
                    status = HttpStatusCode.NotFound
                )

                is EmployeeCreateException -> call.respondText(
                    " ${cause.message}",
                    status = HttpStatusCode.NotFound
                )

                is EmployeeUpdateException -> call.respondText(
                    " ${cause.message}",
                    status = HttpStatusCode.NotFound
                )

                is EmployeeDeleteException -> call.respondText(
                    " ${cause.message}",
                    status = HttpStatusCode.NotFound
                )

                is EmployeeNotFoundException -> call.respondText(
                    "${cause.message}",
                    status = HttpStatusCode.BadRequest
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
