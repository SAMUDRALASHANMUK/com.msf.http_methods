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

fun Application.configurePostRoutes() {
    routing {
        val postService: PostService by inject()

        route(POST) {

            post {
                val post = call.receive<Post>()
                val response = postService.createPost(post)
                call.respond(HttpStatusCode.Created, response)
            }

            get {
                val posts = postService.getAllPosts()
                call.respond(posts)
            }

            get("/{id}") {
                val postId = call.parameters["id"]?.toIntOrNull() ?: return@get call.respondText(
                    "please provide post id",
                    status = HttpStatusCode.BadRequest
                )
                val post = postService.getPostById(postId)
                call.respond(post)
            }


            put("/{id}") {

                val postId = call.parameters["id"]?.toIntOrNull() ?: return@put call.respondText(
                    "please provide post id to update",
                    status = HttpStatusCode.BadRequest
                )
                val postRequest = call.receive<Post>()
                val post = postService.editPost(postRequest)
                call.respond(post)
            }

            delete("/{id}") {
                val postId = call.parameters["id"]?.toIntOrNull() ?: return@delete call.respondText(
                    "please provide post id to delete",
                    status = HttpStatusCode.BadRequest
                )
                val success = postService.deletePost(postId)
                call.respond(success, "Post deleted")
            }
        }
    }
}





