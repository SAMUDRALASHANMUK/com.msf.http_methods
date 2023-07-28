package com.msf.data.methods

import com.google.gson.Gson
import com.msf.data.model.PostClientTest
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.gson.*

suspend fun getData(): Array<PostClientTest> {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            gson()
        }
    }

    val response = client.get("https://jsonplaceholder.typicode.com/posts")
    return Gson().fromJson(response.bodyAsText(), Array<PostClientTest>::class.java)

}