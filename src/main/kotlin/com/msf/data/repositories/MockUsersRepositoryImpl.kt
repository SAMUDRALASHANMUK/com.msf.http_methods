package com.msf.data.repositories

import com.msf.data.model.User
import com.msf.domain.interfaces.UsersRepository

open class MockUsersRepository : UsersRepository {
    private val mockUsers = mutableListOf(
        User(1, "User1", "user1@example.com"),
        User(2, "User2", "user2@example.com"),
        User(3, "User3", "user3@example.com")
    )

    override suspend fun getAllUsers(): List<User> = mockUsers

    override suspend fun getUserById(userId: Int): User? = mockUsers.find { it.user_id == userId }

    override suspend fun createUser(userName: String, email: String): User? {
        val newUser = User(mockUsers.size + 1, userName, email)
        mockUsers.add(newUser)
        return newUser
    }

    override suspend fun editUser(userId: Int, userName: String, email: String): Boolean {
        val userIndex = mockUsers.indexOfFirst { it.user_id == userId }
        if (userIndex != -1) {
            mockUsers[userIndex] = User(userId, userName, email)
            return true
        }
        return false
    }

    override suspend fun deleteUser(userId: Int): Boolean {
        val userToRemove = mockUsers.find { it.user_id == userId }
        if (userToRemove != null) {
            mockUsers.remove(userToRemove)
            return true
        }
        return false
    }
}