package com.msf.util.helperfunctions

import com.google.gson.Gson
import com.msf.data.model.PostClientTest
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.gson.gson

suspend fun getData(): Array<PostClientTest> {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            gson()
        }
    }
    val response = client.get("https://jsonplaceholder.typicode.com/posts")
    return Gson().fromJson(response.bodyAsText(), Array<PostClientTest>::class.java)
}
