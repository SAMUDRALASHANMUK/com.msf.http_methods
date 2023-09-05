package com.msf.routes


import com.msf.model.User
import com.msf.model.UserInput
import com.msf.services.UserService
import com.msf.util.appconstants.ApiEndPoints.DELETE_USER
import com.msf.util.appconstants.ApiEndPoints.GET_USER
import com.msf.util.appconstants.ApiEndPoints.UPDATE_USER
import com.msf.util.appconstants.ApiEndPoints.USER
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.routing
import io.ktor.server.routing.route
import io.ktor.server.routing.get
import io.ktor.server.routing.put
import io.ktor.server.routing.delete
import io.ktor.server.routing.post
import org.koin.ktor.ext.inject
import java.util.*


fun Application.configureUsersRoutes() {

    val userService: UserService by inject()

    routing {
        route(USER) {
            get {
                userService.getAllUsers().apply { call.respond(this) }
            }

            get(GET_USER) {
                val userId =
                    runCatching { UUID.fromString(call.parameters["id"]) }.getOrNull() ?: return@get call.respondText(
                        "Please provide user Id",
                        status = HttpStatusCode.BadRequest
                    )
                userService.getUserById(userId).apply { call.respond(this) }

            }

            post {
                val user = call.receive<UserInput>()
                userService.createUser(user).apply { call.respond(HttpStatusCode.Created, this) }
            }

            put(UPDATE_USER) {
                val userId =
                    runCatching { UUID.fromString(call.parameters["id"]) }.getOrNull() ?: return@put call.respond(
                        status = HttpStatusCode.BadRequest,
                        "please provide id to update"
                    )
                val user = call.receive<User>()
                userService.updateUser(userId, user).apply { call.respond(this) }
            }

            delete(DELETE_USER) {
                val userId = runCatching { UUID.fromString(call.parameters["id"]) }.getOrNull()
                    ?: return@delete call.respondText(
                        "Please provide user id to delete",
                        status = HttpStatusCode.BadRequest
                    )
                userService.deleteUser(userId).apply { call.respond(this) }
            }
        }
    }
}


