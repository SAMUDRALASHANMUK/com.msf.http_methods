package com.msf.routes

import com.msf.model.Profile
import com.msf.services.ProfileService
import com.msf.util.appconstants.ApiEndPoints.PROFILE
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.routing
import io.ktor.server.routing.route
import io.ktor.server.routing.get
import io.ktor.server.routing.put
import io.ktor.server.routing.delete
import io.ktor.server.routing.post
import org.koin.ktor.ext.inject


fun Application.configureProfileRoutes() {

    val profileService: ProfileService by inject()
    routing {
        route(PROFILE) {
            get {
                val profiles = profileService.getAllProfiles()
                call.respond(profiles)
            }

            get("/{id}") {
                val profileId = call.parameters["id"]?.toIntOrNull() ?: return@get call.respondText(
                    "Please provide profile id",
                    status = HttpStatusCode.BadRequest
                )
                val profile = profileService.getProfileById(profileId)
                call.respond(profile)
            }

            post {
                val profile = call.receive<Profile>()
                val response = profileService.createProfile(profile)
                call.respond(status = HttpStatusCode.Created, response)
            }

            put("/{id}") {
                val profileId = call.parameters["id"]?.toIntOrNull() ?: return@put call.respondText(
                    "Please provide profile id",
                    status = HttpStatusCode.BadRequest
                )
                val profile = call.receive<Profile>()
                val response = profileService.updateUser(profileId, profile)
                call.respond(response)
            }

            delete("/{id}") {
                val profileId = call.parameters["id"]?.toIntOrNull() ?: return@delete call.respondText(
                    "please provide id",
                    status = HttpStatusCode.BadRequest
                )
                val response = profileService.deleteProfile(profileId)
                call.respond(response)
            }
        }
    }
}


