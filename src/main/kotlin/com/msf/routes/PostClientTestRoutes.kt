package com.msf.routes

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.msf.data.methods.getData


fun Application.configurePostClientRoutes() {
    routing {
        get("/postClientTest") {
            val posts = getData()
            call.respond(posts)
        }
    }
}
