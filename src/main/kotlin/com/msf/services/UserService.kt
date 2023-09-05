package com.msf.services


import com.msf.exception.UserCreateException
import com.msf.exception.UserDeleteException
import com.msf.exception.UserNotFoundException
import com.msf.exception.UserUpdateException
import com.msf.model.User
import com.msf.repository.UsersRepository
import io.ktor.http.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.UUID

class UserService : KoinComponent {

    private val usersRepository by inject<UsersRepository>()
    suspend fun getAllUsers(): List<User> {
        return usersRepository.getAllUsers()
    }

    suspend fun getUserById(userId: UUID): User {
        val user = usersRepository.getUserById(userId)
        return user ?: throw UserNotFoundException()
    }

    suspend fun createUser(user: User): User {
        val createdUser = usersRepository.createUser(user.userName, user.email)
        return createdUser ?: throw UserCreateException()
    }

    suspend fun updateUser(userId: UUID, user: User): HttpStatusCode {
        val success = usersRepository.editUser(userId, user.userName, user.email)
        return if (success) {
            HttpStatusCode.OK
        } else {
            throw UserUpdateException()
        }
    }

    suspend fun deleteUser(userId: UUID): HttpStatusCode {
        val success = usersRepository.deleteUser(userId)
        return if (success) {
            HttpStatusCode.OK
        } else {
            throw UserDeleteException()
        }
    }
}
