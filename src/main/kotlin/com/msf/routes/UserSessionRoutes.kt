package com.msf.routes

import com.msf.util.redis.removeSessionFromRedis
import com.msf.util.redis.saveSessionToRedis
import com.msf.data.model.UserSession
import io.ktor.server.application.Application
import io.ktor.server.routing.routing
import io.ktor.server.routing.get
import io.ktor.server.application.call
import io.ktor.server.response.respondRedirect
import io.ktor.server.response.respond
import io.ktor.server.sessions.sessions
import io.ktor.server.sessions.set
import io.ktor.server.sessions.get
import io.ktor.server.sessions.clear


fun Application.configureUserSessionRoutes() {
    routing {
        get("/login") {
            call.sessions.set(UserSession(userName = "Shanmuk", password = "Shanmuk123#"))
            call.respondRedirect("/user")
        }

        get("/user") {
            val userSession = call.sessions.get<UserSession>()
            if (userSession != null) {
                // Save the userSession to Redis when session exists
                saveSessionToRedis(userSession)
            }
            call.respond(userSession ?: "Not logged in")
        }

        get("/logout") {
            val userSession = call.sessions.get<UserSession>()
            if (userSession != null) {
                // Remove the userSession from Redis
                removeSessionFromRedis(userSession.sessionId)
            }
            call.sessions.clear<UserSession>()
            call.respondRedirect("/user")
        }
    }
}


