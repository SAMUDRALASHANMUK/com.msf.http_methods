package com.msf.routes

import io.ktor.server.application.call
import io.ktor.server.application.Application
import io.ktor.server.response.respond
import com.msf.util.helperfunctions.getData
import io.ktor.server.routing.routing
import io.ktor.server.routing.get


fun Application.configurePostClientRoutes() {
    routing {
        get("/postClientTest") {
            getData().apply { call.respond(this) }
        }
    }
}
