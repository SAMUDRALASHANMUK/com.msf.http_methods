package com.msf.routes

import com.msf.data.model.Profile
import com.msf.data.repositories.ProfileRepositoryImpl
import com.msf.domain.exceptions.PostCreationException
import com.msf.domain.exceptions.ProfileNotFoundException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.configureProfileRoutes() {
    val profileRepository = ProfileRepositoryImpl()
    routing {
        route("/profiles") {
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
                val existingUser = profileRepository.getProfileByUserId(profile.user_id)

                if (existingUser != null) {
                    call.respond("User account already exists.")
                } else {
                    val createdProfile = profileRepository.createProfile(profile.user_id, profile.profile_data)
                    if (createdProfile != null) {
                        call.respond(HttpStatusCode.Created, createdProfile)
                    } else {
                        throw PostCreationException("Unable to create post")
                    }
                }
            }

            put("/{id}") {
                val profileId = call.parameters["id"]?.toIntOrNull()
                if (profileId != null) {
                    val profile = call.receive<Profile>()
                    val success = profileRepository.editProfile(profileId, profile.profile_data)
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



