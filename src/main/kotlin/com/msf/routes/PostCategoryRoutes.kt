package com.msf.routes

import com.msf.model.PostCategory
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
import java.util.*

fun Application.configurePostCategoryRoutes() {

    val postCategoryService: PostCategoryService by inject()

    routing {
        get(CATEGORY_POSTS_PATH) {
            val categoryId = runCatching { UUID.fromString(call.parameters["category_id"]) }?.getOrNull()
                ?: return@get call.respond(
                    HttpStatusCode.BadRequest,
                    "please provide category_id"
                )

            postCategoryService.getPostsForCategory(categoryId)
                .apply { call.respond(HttpStatusCode.OK, this) }
        }

        get(POST_CATEGORIES_PATH) {
            val postId =
                runCatching { UUID.fromString(call.parameters["post_id"]) }?.getOrNull() ?: return@get call.respond(
                    HttpStatusCode.BadRequest,
                    "Please provide post id"
                )
            postCategoryService.getCategoriesForPost(postId)
                .apply { call.respond(HttpStatusCode.OK, this) }
        }
        post {
            val postCategory = call.receive<PostCategory>()
            postCategoryService.createPostCategory(postCategory)
                .apply { call.respond(this) }
        }
    }
}

