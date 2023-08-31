package com.msf.services

import com.msf.config.status.UserDeletionException
import com.msf.config.status.UserNotFoundException
import com.msf.model.CreateUserResponse
import com.msf.model.User
import com.msf.repository.UsersRepositoryImpl
import io.ktor.http.*

class UserService() {

    private val usersRepository = UsersRepositoryImpl()
    suspend fun getAllUsers(): List<User> {
        return usersRepository.getAllUsers()
    }

    suspend fun getUserById(userId: Int): User {
        val user = usersRepository.getUserById(userId)
        if (user != null) {
            return user
        } else {
            throw UserNotFoundException()
        }
    }

    suspend fun createUser(user: User): CreateUserResponse {
        val createdUser = usersRepository.createUser(user.userName, user.email)
        return if (createdUser != null) {
            CreateUserResponse(HttpStatusCode.Created, createdUser)
        } else {
            CreateUserResponse(HttpStatusCode.InternalServerError, null)
        }
    }

    suspend fun updateUser(userId: Int, user: User): HttpStatusCode {
        val success = usersRepository.editUser(userId, user.userName, user.email)
        return if (success) {
            HttpStatusCode.OK
        } else {
            throw UserNotFoundException()
        }
    }


    suspend fun deleteUser(userId: Int): HttpStatusCode {
        val success = usersRepository.deleteUser(userId)
        return if (success) {
            HttpStatusCode.OK
        } else {
            throw UserDeletionException()
        }
    }
}
