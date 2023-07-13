package com.msf.dao

import com.google.gson.Gson
import com.msf.model.Post
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.gson.*
import io.ktor.server.engine.*

suspend fun getData(): Array<Post> {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            gson()
        }
    }
    val response = client.get("https://jsonplaceholder.typicode.com/posts")
    return Gson().fromJson(response.bodyAsText(), Array<Post>::class.java)

}