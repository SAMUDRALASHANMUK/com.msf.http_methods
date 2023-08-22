package com.msf.routes

import io.ktor.server.application.call
import io.ktor.server.application.Application
import io.ktor.server.response.respond
import com.msf.data.methods.getData
import io.ktor.server.routing.routing
import io.ktor.server.routing.get


fun Application.configurePostClientRoutes() {
    routing {
        get("/postClientTest") {
            val posts = getData()
            call.respond(posts)
        }
    }
}
