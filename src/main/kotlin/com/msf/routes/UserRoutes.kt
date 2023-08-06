package com.msf.routes

import com.msf.data.model.User
import com.msf.data.repositories.UsersRepositoryImpl
import com.msf.domain.exceptions.UserNotFoundException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject


fun Application.configureUsersRoutes() {

   val usersRepository: UsersRepositoryImpl by inject()
    //val usersRepository = UsersRepositoryImpl()


    routing {
        route("/users") {
            get("/") {
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
                        throw UserNotFoundException("User with ID $userId not found")
                    }
                } else {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }

            post("/") {
                val user = call.receive<User>()
                val createdUser = usersRepository.createUser(user.user_name, user.email)
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
                    val success = usersRepository.editUser(userId, user.user_name, user.email)
                    if (success) {
                        call.respond(HttpStatusCode.OK)
                    } else {
                        call.respond(HttpStatusCode.NotFound)
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
                        call.respond(HttpStatusCode.NotFound)
                    }
                } else {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }
        }
    }
}
