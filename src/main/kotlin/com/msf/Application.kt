package com.msf

import com.google.gson.Gson
import com.msf.model.Post
import com.msf.plugins.configureContentNegotiation
import com.msf.plugins.configureRequestValidation
import com.msf.plugins.configureStatusPages
import com.msf.routes.configureEmpRoutes
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    configureContentNegotiation()
    configureRequestValidation()
    configureStatusPages()
    configureEmpRoutes()
}

suspend fun getData(): Array<Post> {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            gson()
        }
    }
    val response = client.get("https://jsonplaceholder.typicode.com/posts")
    return Gson().fromJson(response.bodyAsText(), Array<Post>::class.java)
}



