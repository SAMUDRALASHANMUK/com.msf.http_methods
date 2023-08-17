package com.msf

import com.msf.data.MockDatabaseFactory
import com.msf.data.model.User
import com.msf.data.repositories.UsersRepositoryImpl
import com.msf.domain.interfaces.UsersRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class UsersRepositoryTest {

    private val mockUsers = listOf(
        User(1, "User1", "user1@example.com"),
        User(2, "User2", "user2@example.com"),
        User(3, "User3", "user3@example.com")
    )

    private lateinit var usersRepository: UsersRepository

    @Before
    fun setUp() {
        MockDatabaseFactory.init() // Initialize the mock database
        MockKAnnotations.init(this)

        // Initialize your repository with a mocked database query
        usersRepository = UsersRepositoryImpl()

        // Mock the behavior of the database query
        coEvery { MockDatabaseFactory.dbQuery<List<User>>(any()) } returns mockUsers
    }

    @Test
    fun testGetAllUsers() = runBlocking {
        // Perform the test
        val users = usersRepository.getAllUsers()

        // Verify the result
        assertEquals(mockUsers, users)
    }
}
