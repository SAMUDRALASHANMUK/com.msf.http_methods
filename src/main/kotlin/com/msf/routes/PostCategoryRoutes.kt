package com.msf.routes

import com.msf.data.model.PostCategory
import com.msf.data.repositories.PostCategoriesRepositoryImpl
import com.msf.domain.exceptions.PostCategoryCreateException
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.routing
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import org.koin.ktor.ext.inject


fun Application.configurePostCategoryRoutes() {

    val postCategoriesRepository: PostCategoriesRepositoryImpl by inject()

    routing {
        get("/categories/{category_id}/posts") {
            val categoryId = call.parameters["category_id"]?.toIntOrNull()

            if (categoryId == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid category_id")
                return@get
            }

            val associatedPosts = postCategoriesRepository.getPostsForCategory(categoryId)
            call.respond(HttpStatusCode.OK, associatedPosts)
        }

        get("/posts/{post_id}/categories") {
            val postId = call.parameters["post_id"]?.toIntOrNull()

            if (postId == null) {
                call.respond(HttpStatusCode.BadRequest, "Invalid post_id")
                return@get
            }

            val associatedCategories = postCategoriesRepository.getCategoriesForPost(postId)
            call.respond(HttpStatusCode.OK, associatedCategories)
        }
        post("/") {
            val postCategory = call.receive<PostCategory>()
            val createdUser =
                postCategoriesRepository.associatePostWithCategory(postCategory.post_id, postCategory.category_id)
            if (createdUser != null) {
                call.respond(HttpStatusCode.Created, createdUser)
            } else {
                throw PostCategoryCreateException()
            }
        }
    }
}

