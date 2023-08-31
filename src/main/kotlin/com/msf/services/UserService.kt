package com.msf.services

import com.msf.config.status.UserCreateException
import com.msf.config.status.UserDeleteException
import com.msf.config.status.UserNotFoundException
import com.msf.config.status.UserUpdateException
import com.msf.model.User
import com.msf.repository.UsersRepositoryImpl
import io.ktor.http.*

class UserService {

    private val usersRepository = UsersRepositoryImpl()
    suspend fun getAllUsers(): List<User> {
        return usersRepository.getAllUsers()
    }

    suspend fun getUserById(userId: Int): User {
        val user = usersRepository.getUserById(userId)
        return user ?: throw UserNotFoundException()
    }

    suspend fun createUser(user: User): User {
        val createdUser = usersRepository.createUser(user.userName, user.email)
        return createdUser ?: throw UserCreateException()
    }

    suspend fun updateUser(userId: Int, user: User): HttpStatusCode {
        val success = usersRepository.editUser(userId, user.userName, user.email)
        return if (success) {
            HttpStatusCode.OK
        } else {
            throw UserUpdateException()
        }
    }

    suspend fun deleteUser(userId: Int): HttpStatusCode {
        val success = usersRepository.deleteUser(userId)
        return if (success) {
            HttpStatusCode.OK
        } else {
            throw UserDeleteException()
        }
    }
}
