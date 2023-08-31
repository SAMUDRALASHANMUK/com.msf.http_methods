package com.msf.dao

import com.msf.data.model.User


interface UserDAO {
    suspend fun createUser(username: String, email: String): User?
    suspend fun getUserById(userId: Int): User?

    suspend fun getAllUsers():List<User>
    suspend fun editUser(userId: Int, newUsername: String, newEmail: String): Boolean
    suspend fun deleteUser(userId: Int): Boolean
}
