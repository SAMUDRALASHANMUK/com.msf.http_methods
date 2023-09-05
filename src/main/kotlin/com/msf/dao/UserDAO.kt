package com.msf.dao

import com.msf.model.User
import java.util.UUID


interface UserDAO {
    suspend fun createUser(username: String, email: String): User?
    suspend fun getUserById(userId: UUID): User?

    suspend fun getAllUsers(): List<User>
    suspend fun editUser(userId: UUID, newUsername: String, newEmail: String): Boolean
    suspend fun deleteUser(userId: UUID): Boolean
}
