package com.msf.routes

import com.msf.model.PostCategory
import com.msf.repository.PostCategoriesRepositoryImpl
import com.msf.services.PostCategoryService
import com.msf.util.appconstants.ApiEndPoints.CATEGORY_POSTS_PATH
import com.msf.util.appconstants.ApiEndPoints.POST_CATEGORIES_PATH
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

    val postCategoryService: PostCategoryService by inject()

    routing {
        get(CATEGORY_POSTS_PATH) {
            val categoryId = call.parameters["category_id"]?.toIntOrNull() ?: return@get call.respond(
                HttpStatusCode.BadRequest,
                "please provide category_id"
            )

            val associatedPosts = postCategoryService.getPostsForCategory(categoryId)
            call.respond(HttpStatusCode.OK, associatedPosts)
        }

        get(POST_CATEGORIES_PATH) {
            val postId = call.parameters["post_id"]?.toIntOrNull() ?: return@get call.respond(
                HttpStatusCode.BadRequest,
                "Please provide post id"
            )
            val associatedCategories = postCategoryService.getCategoriesForPost(postId)
            call.respond(HttpStatusCode.OK, associatedCategories)
        }
        post {
            val postCategory = call.receive<PostCategory>()
            val response = postCategoryService.createPostCategory(postCategory)
            call.respond(response)
        }
    }
}

