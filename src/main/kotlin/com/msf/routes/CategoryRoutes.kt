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

fun Application.configureCategoryRoutes() {
    val categoryService: CategoryService by inject()
    routing {
        route(CATEGORY) {
            get {
                val categories = categoryService.getAllCategories()
                call.respond(HttpStatusCode.OK, categories)
            }
            post {
                val category = call.receive<Category>()
                val response = categoryService.createCategory(category)
                call.respond(HttpStatusCode.Created, response)
            }
            get(GET_CATEGORY) {
                val categoryId = call.parameters["id"]?.toIntOrNull() ?: return@get call.respondText(
                    "No parameters",
                    status = HttpStatusCode.BadRequest
                )
                val category = categoryService.getCategoryById(categoryId)
                call.respond(category)
            }

            put(UPDATE_CATEGORY) {
                val categoryId = call.parameters["id"]?.toIntOrNull() ?: return@put
                val requestCategory = call.receive<Category>()
                val response = categoryService.updateCategory(categoryId, requestCategory)
                call.respond(response)
            }

            delete(DELETE_CATEGORY) {
                val categoryId = call.parameters["categoryId"]?.toIntOrNull()
                    ?: return@delete call.respondText("Please provide categoryId", status = HttpStatusCode.BadRequest)
                val response = categoryService.deleteCategory(categoryId)
                call.respond(response, HttpStatusCode.OK)
            }
        }
    }
}

