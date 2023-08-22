package com.msf.routes


import com.msf.data.model.Category
import com.msf.data.repositories.CategoryRepositoryImpl
import com.msf.util.appconstants.ApiEndPoints.CATEGORY
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
    val categoriesRepository: CategoryRepositoryImpl by inject()
    routing {
        route(CATEGORY) {
            get {
                val categories = categoriesRepository.getAllCategories()
                call.respond(HttpStatusCode.OK, categories)
            }
            post {
                val requestCategory = call.receive<Category>()
                val addedCategory =
                    categoriesRepository.addCategory(
                        requestCategory.categoryName
                    )
                if (addedCategory != null) {
                    call.respond(HttpStatusCode.Created, addedCategory)
                } else {
                    call.respond(HttpStatusCode.BadRequest, "Failed to add category.")
                }
            }
            get("/{id}") {
                val categoryId = call.parameters["id"] ?: return@get call.respondText(
                    "No parameters",
                    status = HttpStatusCode.BadRequest
                )
                val category = categoriesRepository.getCategoryById(categoryId.toInt())
                if (category != null) {
                    call.respond(HttpStatusCode.OK, category)
                } else {
                    call.respond(HttpStatusCode.NotFound, "Category not found.")
                }
            }

            put("/{categoryId}") {
                val categoryId = call.parameters["categoryId"]?.toIntOrNull()
                val requestCategory = call.receive<Category>()
                val updated = categoriesRepository.updateCategory(
                    categoryId!!,
                    requestCategory.categoryName
                )
                if (updated) {
                    call.respond(HttpStatusCode.OK, "Category updated successfully.")
                } else {
                    call.respond(HttpStatusCode.NotFound, "Category not found.")
                }
            }

            delete("/{categoryId}") {
                val categoryId = call.parameters["categoryId"]?.toIntOrNull()
                val deleted = categoriesRepository.removeCategory(categoryId!!)
                if (deleted) {
                    call.respond(HttpStatusCode.OK)
                } else {
                    call.respond(HttpStatusCode.NotFound)
                }
            }
        }
    }
}

