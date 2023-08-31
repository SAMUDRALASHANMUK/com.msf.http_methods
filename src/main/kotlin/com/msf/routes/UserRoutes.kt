package com.msf.routes


import com.msf.model.User
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


fun Application.configureUsersRoutes() {

    val userService: UserService by inject()

    routing {
        route(USER) {
            get {
                val users = userService.getAllUsers()
                call.respond(users)
            }

            get(GET_USER) {
                val userId = call.parameters["id"]?.toIntOrNull() ?: return@get call.respondText(
                    "Please provide user Id",
                    status = HttpStatusCode.BadRequest
                )
                val user = userService.getUserById(userId)
                call.respond(user)
            }

            post {
                val user = call.receive<User>()
                val response = userService.createUser(user)
                call.respond(HttpStatusCode.Created, response)
            }

            put(UPDATE_USER) {
                val userId = call.parameters["id"]?.toIntOrNull() ?: return@put call.respond(
                    status = HttpStatusCode.BadRequest,
                    "please provide id to update"
                )
                val user = call.receive<User>()
                val status = userService.updateUser(userId, user)
                call.respond(status)
            }

            delete(DELETE_USER) {
                val userId = call.parameters["id"]?.toIntOrNull() ?: return@delete call.respondText(
                    "Please provide user id to delete",
                    status = HttpStatusCode.BadRequest
                )
                var status = userService.deleteUser(userId)
                call.respond(status)
            }
        }
    }
}


