package com.msf.routes

import com.msf.data.model.Categorie
import com.msf.data.repositories.CategoryRepositoryImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureCategoryRoutes() {
    val categoriesRepositoryImpl = CategoryRepositoryImpl()
    routing {

        route("/categories") {
            get {
                val categories = categoriesRepositoryImpl.getAllCategories()
                call.respond(HttpStatusCode.OK, categories)
            }
            post {
                val requestCategory = call.receive<Categorie>()
                val addedCategory =
                    categoriesRepositoryImpl.addCategory(
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
                    val category = categoriesRepositoryImpl.getCategoryById(categoryId)
                    if (category != null) {
                        call.respond(HttpStatusCode.OK, category)
                    } else {
                        call.respond(HttpStatusCode.NotFound, "Category not found.")
                    }
                } else {
                    call.respond(HttpStatusCode.BadRequest, "Invalid category ID.")
                }
            }
            put("/") {
                val categoryId = call.parameters["id"]?.toIntOrNull()
                if (categoryId != null) {
                    val requestCategory = call.receive<Categorie>()
                    val updated = categoriesRepositoryImpl.updateCategory(
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
            delete("/") {
                val categoryId = call.parameters["id"]?.toIntOrNull()
                if (categoryId != null) {
                    val removed = categoriesRepositoryImpl.removeCategory(categoryId)
                    if (removed) {
                        call.respond(HttpStatusCode.OK, "Category removed successfully.")
                    } else {
                        call.respond(HttpStatusCode.NotFound, "Category not found.")
                    }
                } else {
                    call.respond(HttpStatusCode.BadRequest, "Invalid category ID.")
                }
            }
        }
    }
}