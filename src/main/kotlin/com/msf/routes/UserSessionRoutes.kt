package com.msf.routes

import com.msf.data.methods.removeSessionFromRedis
import com.msf.data.methods.saveSessionToRedis
import com.msf.data.model.UserSession
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.sessions.*

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


