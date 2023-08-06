package com.msf

import com.msf.data.model.User
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.junit.Test
import kotlin.test.assertEquals

class ApplicationTest {

    @Test
    fun `test simple GET should return list of users`() = testApplication {

        val response = client.get("/users/") {
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
        }
        assertEquals(HttpStatusCode.OK, response.status)
    }

    @Test
    fun `test POST should add a new user`() = testApplication {

        val user1 = User(5, "Jet", "Brains@gmail.com")
        val serializedUser = Json.encodeToString(user1)
        val response = client.post("/users/") {
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(serializedUser)
        }
        assertEquals(HttpStatusCode.Created, response.status)
        val responseUser = Json.decodeFromString<User>(response.bodyAsText())
        assertEquals(user1, responseUser)
    }

    @Test
    fun `test GET all should return user based on id`() = testApplication {

        val user1 = User(5, "Jet", "Brains@gmail.com")
        val response = client.get("/users/5") {
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
        }
        assertEquals(HttpStatusCode.OK, response.status)
        val responseUser = Json.decodeFromString<User>(response.bodyAsText())
        assertEquals(user1, responseUser)
    }

    @Test
    fun `test DELETE should remove the user`() = testApplication {

        val response = client.delete("/users/1") {
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
        }
        assertEquals(HttpStatusCode.OK, response.status)

    }

    @Test
    fun `test PUT should update the user`() = testApplication {

        val user = User(6, "John", "john@example.com")
        val updatedUser = User(1, "Updated John", "updatedjohn@example.com")
        val serializedUser = Json.encodeToString(updatedUser)
        val response = client.put("users/6") {
            headers[HttpHeaders.ContentType] = ContentType.Application.Json.toString()
            setBody(serializedUser)
        }
        assertEquals(HttpStatusCode.OK, response.status)

    }
}











