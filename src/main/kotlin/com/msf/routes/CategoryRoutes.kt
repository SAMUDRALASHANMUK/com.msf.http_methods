package com.msf.routes

import com.msf.model.Category
import com.msf.services.CategoryService
import com.msf.util.appconstants.ApiEndPoints.CATEGORY
import com.msf.util.appconstants.ApiEndPoints.DELETE_CATEGORY
import com.msf.util.appconstants.ApiEndPoints.GET_CATEGORY
import com.msf.util.appconstants.ApiEndPoints.UPDATE_CATEGORY
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
import java.util.UUID

fun Application.configureCategoryRoutes() {
    val categoryService: CategoryService by inject()
    routing {
        route(CATEGORY) {
            get {
                categoryService.getAllCategories().apply { call.respond(HttpStatusCode.OK, this) }
            }
            post {
                val category = call.receive<Category>()
                categoryService.createCategory(category).apply { call.respond(HttpStatusCode.Created, this) }
            }
            get(GET_CATEGORY) {
                val categoryId =
                    runCatching { UUID.fromString(call.parameters["id"]) }.getOrNull() ?: return@get call.respondText(
                        "No parameters",
                        status = HttpStatusCode.BadRequest
                    )
                categoryService.getCategoryById(categoryId).apply { call.respond(this) }
            }

            put(UPDATE_CATEGORY) {
                val categoryId = runCatching { UUID.fromString(call.parameters["id"]) }.getOrNull() ?: return@put
                val requestCategory = call.receive<Category>()
                categoryService.updateCategory(categoryId, requestCategory).apply { call.respond(this) }
            }

            delete(DELETE_CATEGORY) {
                val categoryId = runCatching { UUID.fromString(call.parameters["id"]) }.getOrNull()
                    ?: return@delete call.respondText("Please provide categoryId", status = HttpStatusCode.BadRequest)
                categoryService.deleteCategory(categoryId).apply { call.respond(this, HttpStatusCode.OK) }
            }
        }
    }
}

