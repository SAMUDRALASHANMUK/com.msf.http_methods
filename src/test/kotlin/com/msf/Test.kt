package com.msf

import com.msf.model.User
import com.msf.repository.UsersRepository
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Test
import kotlin.test.assertEquals

class Test {

    private val usersRepository = mockk<UsersRepository>()

    @Test
    fun testGetAllUsers() = testApplication {
        coEvery { usersRepository.getAllUsers() } returns listOf(
            User(1, "User1", "user1@example.com"),
            User(2, "User2", "user2@example.com"),
            User(3, "User3", "user3@example.com")
        )
        val response = client.get("/users/")
        assertEquals(HttpStatusCode.OK, response.status)

        val expectedResponseJson = """
        [
            {"user_id":1,"user_name":"User1","email":"user1@example.com"},
            {"user_id":2,"user_name":"User2","email":"user2@example.com"},
            {"user_id":3,"user_name":"User3","email":"user3@example.com"}
        ] """.trimIndent()

        val actualResponseJson = response.bodyAsText() ?: ""
        val expectedList = Json.decodeFromString<List<User>>(expectedResponseJson)
        val actualList = Json.decodeFromString<List<User>>(actualResponseJson)

        assertEquals(expectedList.sortedBy { it.userName }, actualList.sortedBy { it.userName })
    }

    @Test
    fun testGetUserById_UserFound() = testApplication {
        coEvery { usersRepository.getUserById(2) } returns User(2, "User2", "user2@example.com")

        val response = client.get("users/2") {
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
        }

        assertEquals(HttpStatusCode.OK, response.status)
        val expectedUser = User(2, "User2", "user2@example.com")
        val actualUser = Json.decodeFromString<User>(response.bodyAsText())
        assertEquals(expectedUser, actualUser)
    }

    @Test
    fun testGetUserById_UserNotFound() = testApplication {
        coEvery { usersRepository.getUserById(4) } returns null
        val response = client.get("/users/4")
        assertEquals(HttpStatusCode.NotFound, response.status)
    }


    @Test
    fun testCreateUser() = testApplication {
        coEvery { usersRepository.createUser("shanmuk", "user4@example.com") } returns User(
            4,
            "shanmuk",
            "user4@example.com"
        )

        val response = client.post("/users") {
            setBody(Json.encodeToString(User(4, "shanmuk", "user4@example.com")))
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
        }
        println(response.bodyAsText())
        assertEquals(HttpStatusCode.Created, response.status)

        val expectedUser = User(4, "shanmuk", "user4@example.com")
        val actualUser = Json.decodeFromString<User>(response.bodyAsText())
        assertEquals(expectedUser, actualUser)
    }

    @Test
    fun testUpdateUser() = testApplication {
        coEvery { usersRepository.editUser(3, "UpdatedUser", "updated@example.com") } returns true
        val response = client.put("/users/3") {
            setBody(Json.encodeToString(User(3, "UpdatedUser", "updated@example.com")))
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
        }
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun testUpdateUser_UserNotFound() = testApplication {
        coEvery { usersRepository.editUser(5, any(), any()) } returns false
        val response = client.put("/users/5") {
            setBody(Json.encodeToString(User(5, "UpdatedUser", "updated@example.com")))
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
        }
        assertEquals(HttpStatusCode.NotFound, response.status)
    }


    @Test
    fun testDeleteUser() = testApplication {
        coEvery { usersRepository.deleteUser(2) } returns true
        val response = client.delete("/users/2")
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun testDeleteUser_UserNotFound() = testApplication {
        coEvery { usersRepository.deleteUser(6) } returns false
        val response = client.delete("/users/6")
        assertEquals(HttpStatusCode.NotFound, response.status)

    }
}