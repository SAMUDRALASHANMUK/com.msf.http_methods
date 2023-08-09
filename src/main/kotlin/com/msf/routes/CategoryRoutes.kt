package com.msf.routes

import com.msf.data.model.Categorie
import com.msf.data.repositories.CategoryRepositoryImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureCategoryRoutes() {

    val categoriesRepository: CategoryRepositoryImpl by inject()
    routing {

        route("/categories") {
            get {
                val categories = categoriesRepository.getAllCategories()
                call.respond(HttpStatusCode.OK, categories)
            }
            post {
                val requestCategory = call.receive<Categorie>()
                val addedCategory =
                    categoriesRepository.addCategory(
                        requestCategory.category_name,
                        requestCategory.post_id
                    )
                if (addedCategory != null) {
                    call.respond(HttpStatusCode.Created, addedCategory)
                } else {
                    call.respond(HttpStatusCode.BadRequest, "Failed to add category.")
                }
            }
            get("/{id}") {
                val categoryId = call.parameters["id"]?.toIntOrNull()
                if (categoryId != null) {
                    val category = categoriesRepository.getCategoryById(categoryId)
                    if (category != null) {
                        call.respond(HttpStatusCode.OK, category)
                    } else {
                        call.respond(HttpStatusCode.NotFound, "Category not found.")
                    }
                } else {
                    call.respond(HttpStatusCode.BadRequest, "Invalid category ID.")
                }
            }
            put("/{categoryId}") {
                val categoryId = call.parameters["id"]?.toIntOrNull()
                if (categoryId != null) {
                    val requestCategory = call.receive<Categorie>()
                    val updated = categoriesRepository.updateCategory(
                        categoryId,
                        requestCategory.category_name,
                        requestCategory.post_id
                    )
                    if (updated) {
                        call.respond(HttpStatusCode.OK, "Category updated successfully.")
                    } else {
                        call.respond(HttpStatusCode.NotFound, "Category not found.")
                    }
                } else {
                    call.respond(HttpStatusCode.BadRequest, "Invalid category ID.")
                }
            }
            delete("/{categoryId}") {
                val categoryId = call.parameters["categoryId"]?.toIntOrNull()

                if (categoryId != null) {
                    val deleted = categoriesRepository.removeCategory(categoryId)
                    if (deleted) {
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