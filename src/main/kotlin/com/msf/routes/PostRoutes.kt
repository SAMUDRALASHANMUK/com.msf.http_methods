// Import necessary dependencies

import com.msf.data.model.Post
import com.msf.data.repositories.PostRepositoryImpl
import com.msf.domain.exceptions.PostCreationException
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

// Route function for Post table
fun Application.configurePostRoutes() {
    routing {
        val postRepository = PostRepositoryImpl()

        route("/posts") {
            // Create a new post
            // Define a custom exception for post creation failure


            post("/") {

                val post = call.receive<Post>()
                val createdPost = postRepository.createPost(post.user_id, post.title, post.content)
                if (createdPost != null) {
                    call.respond(HttpStatusCode.Created, createdPost)
                } else {
                    throw PostCreationException("Failed to create post.")
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
                    }
                } else {
                    call.respond(HttpStatusCode.BadRequest)
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





