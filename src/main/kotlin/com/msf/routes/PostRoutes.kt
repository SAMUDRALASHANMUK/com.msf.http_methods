package com.msf.routes

import com.msf.model.Post
import com.msf.services.PostService
import com.msf.util.appconstants.ApiEndPoints.POST
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.routing
import io.ktor.server.routing.route
import io.ktor.server.routing.get
import io.ktor.server.routing.put
import io.ktor.server.routing.delete
import io.ktor.server.routing.post
import org.koin.ktor.ext.inject
import java.util.*

fun Application.configurePostRoutes() {
    routing {
        val postService: PostService by inject()

        route(POST) {

            post {
                val post = call.receive<Post>()
                postService.createPost(post)
                    .apply { call.respond(HttpStatusCode.Created, this) }
            }

            get {
                postService.getAllPosts()
                    .apply { call.respond(this) }
            }

            get("/{id}") {
                val postId =
                    runCatching { UUID.fromString(call.parameters["id"]) }.getOrNull() ?: return@get call.respondText(
                        "please provide post id",
                        status = HttpStatusCode.BadRequest
                    )
                postService.getPostById(postId)
                    .apply { call.respond(this) }
            }


            put("/{id}") {

                val postId = call.parameters["id"]?.toIntOrNull() ?: return@put call.respondText(
                    "please provide post id to update",
                    status = HttpStatusCode.BadRequest
                )

                val postRequest = call.receive<Post>()
                postService.editPost(postRequest)
                    .apply { call.respond(this) }
            }

            delete("/{id}") {
                val postId = runCatching { UUID.fromString(call.parameters["id"]) }.getOrNull()
                    ?: return@delete call.respondText(
                        "please provide post id to delete",
                        status = HttpStatusCode.BadRequest
                    )
                postService.deletePost(postId)
                    .apply { call.respond(this, "Post deleted") }
            }
        }
    }
}





