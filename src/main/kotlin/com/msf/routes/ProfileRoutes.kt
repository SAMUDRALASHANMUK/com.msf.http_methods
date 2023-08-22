package com.msf.routes

import com.msf.data.model.Profile
import com.msf.data.repositories.ProfileRepositoryImpl
import com.msf.domain.exceptions.PostCreationException
import com.msf.domain.exceptions.ProfileNotFoundException
import com.msf.util.appconstants.ApiEndPoints.PROFILE
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.routing
import io.ktor.server.routing.route
import io.ktor.server.routing.get
import io.ktor.server.routing.put
import io.ktor.server.routing.delete
import io.ktor.server.routing.post
import org.koin.ktor.ext.inject


fun Application.configureProfileRoutes() {

    val profileRepository: ProfileRepositoryImpl by inject()
    routing {
        route(PROFILE) {
            get("/") {
                val profiles = profileRepository.getAllProfiles()
                call.respond(profiles)
            }

            get("/{id}") {
                val profileId = call.parameters["id"]?.toIntOrNull()
                if (profileId != null) {
                    val profile = profileRepository.getProfileById(profileId)
                    if (profile != null) {
                        call.respond(profile)
                    } else {
                        throw ProfileNotFoundException("Profile with ID $profileId not found")
                    }
                } else {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }

            post("/") {
                val profile = call.receive<Profile>()
                val existingUser = profileRepository.getProfileByUserId(profile.userId)

                if (existingUser != null) {
                    call.respond("User account already exists.")
                } else {
                    val createdProfile = profileRepository.createProfile(profile.userId, profile.profileData)
                    if (createdProfile != null) {
                        call.respond(HttpStatusCode.Created, createdProfile)
                    } else {
                        throw PostCreationException()
                    }
                }
            }

            put("/{id}") {
                val profileId = call.parameters["id"]?.toIntOrNull()
                if (profileId != null) {
                    val profile = call.receive<Profile>()
                    val success = profileRepository.editProfile(profileId, profile.profileData)
                    if (success) {
                        call.respond(HttpStatusCode.OK)
                    } else {
                        call.respond(HttpStatusCode.NotFound)
                    }
                } else {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }

            delete("/{id}") {
                val profileId = call.parameters["id"]?.toIntOrNull()
                if (profileId != null) {
                    val success = profileRepository.deleteProfile(profileId)
                    if (success) {
                        call.respond(HttpStatusCode.OK)
                    } else {
                        call.respond(HttpStatusCode.NotFound)
                    }
                } else {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }
        }
    }
}



