package com.msf.routes

import com.msf.data.model.Post
import com.msf.data.repositories.PostRepositoryImpl
import com.msf.domain.exceptions.PostCreationException
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

fun Application.configurePostRoutes() {
    routing {
        val postRepository: PostRepositoryImpl by inject()


        route("/posts") {
            // Create a new post
            // Define a custom exception for post creation failure

            post("/") {
                val post = call.receive<Post>()

                val createdPost =
                    postRepository.createPost(post.user_id, post.title, post.content)
                if (createdPost != null) {
                    call.respond(HttpStatusCode.Created, createdPost)
                } else {
                    throw PostCreationException()
                }

            }


            // Get all posts
            get("/") {
                val posts = postRepository.getAllPosts()
                call.respond(posts)
            }

            // Get a specific post by post_id
            get("/{id}") {
                val postId = call.parameters["id"]?.toIntOrNull()
                if (postId != null) {
                    val post = postRepository.getPostById(postId)
                    if (post != null) {
                        call.respond(post)
                    } else {
                        call.respond(HttpStatusCode.NotFound)
                        println("Post with ID $postId not found.")
                    }
                } else {
                    call.respond(HttpStatusCode.BadRequest)
                    println("Invalid postId in the request.")
                }
            }

            // Update a post by post_id
            put("/{id}") {

                val postId = call.parameters["id"]?.toIntOrNull()
                if (postId != null) {
                    val postRequest = call.receive<Post>()
                    val success = postRepository.editPost(
                        postId,
                        postRequest.title,
                        postRequest.content
                    )
                    if (success) {
                        call.respond(HttpStatusCode.OK)
                    } else {
                        call.respond(HttpStatusCode.NotFound)
                    }
                } else {
                    call.respond(HttpStatusCode.BadRequest)
                }
            }

            // Delete a post by post_id
            delete("/{id}") {

                val postId = call.parameters["id"]?.toIntOrNull()
                if (postId != null) {
                    val success = postRepository.deletePost(postId)
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





