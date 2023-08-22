package com.msf.routes

import com.msf.data.model.User
import com.msf.data.repositories.UsersRepositoryImpl
import com.msf.domain.exceptions.UserDeletionException
import com.msf.domain.exceptions.UserNotFoundException
import com.msf.util.appconstants.ApiEndPoints
import com.msf.util.appconstants.ApiEndPoints.USER
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.routing
import io.ktor.server.routing.route
import io.ktor.server.routing.get
import io.ktor.server.routing.put
import io.ktor.server.routing.delete
import io.ktor.server.routing.post
import org.koin.ktor.ext.inject


fun Application.configureUsersRoutes() {

    // val usersRepository = MockUsersRepository()
    val usersRepository: UsersRepositoryImpl by inject()

    routing {
        route(USER) {
            get {
                val users = usersRepository.getAllUsers()
                call.respond(users)
            }

            get("/{id}") {
                val userId = call.parameters["id"]?.toIntOrNull()
                if (userId != null) {
                    val user = usersRepository.getUserById(userId)
                    if (user != null) {
                        call.respond(user)
                    } else {
                        throw UserNotFoundException()
                    }
                } else {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }

            post {
                val user = call.receive<User>()
                val createdUser = usersRepository.createUser(user.userName, user.email)
                if (createdUser != null) {
                    call.respond(HttpStatusCode.Created, createdUser)
                } else {
                    call.respond(HttpStatusCode.InternalServerError)
                }
            }

            put("/{id}") {
                val userId = call.parameters["id"]?.toIntOrNull()
                if (userId != null) {
                    val user = call.receive<User>()
                    val success = usersRepository.editUser(userId, user.userName, user.email)
                    if (success) {
                        call.respond(HttpStatusCode.OK)
                    } else {
                        throw UserNotFoundException()
                    }
                } else {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }

            delete("/{id}") {
                val userId = call.parameters["id"]?.toIntOrNull()
                if (userId != null) {
                    val success = usersRepository.deleteUser(userId)
                    if (success) {
                        call.respond(HttpStatusCode.OK)
                    } else {
                        throw UserDeletionException("Unable to delete post with ID $userId")
                    }
                } else {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }
        }
    }
}

