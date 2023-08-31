package com.msf.services

import com.msf.config.status.*
import com.msf.model.Category
import com.msf.repository.CategoryRepositoryImpl
import io.ktor.http.*

class CategoryService {
    private val categoryRepositoryImpl = CategoryRepositoryImpl()
    suspend fun getAllCategories(): List<Category> {
        return categoryRepositoryImpl.getAllCategories()
    }

    suspend fun getCategoryById(id: Int): Category {
        val response = categoryRepositoryImpl.getCategoryById(id)
        return response ?: throw CategoryNotFoundException()
    }

    suspend fun createCategory(category: Category): Category {
        val response = categoryRepositoryImpl.addCategory(category.categoryName)
        return response ?: throw CategoryCreateException()
    }

    suspend fun updateCategory(id: Int, category: Category): HttpStatusCode {
        val response = categoryRepositoryImpl.updateCategory(categoryId = id, category.categoryName)
        return if (response) {
            HttpStatusCode.OK
        } else {
            throw CategoryUpdateException()
        }
    }

    suspend fun deleteCategory(id: Int): HttpStatusCode {
        val response = categoryRepositoryImpl.removeCategory(id)
        return if (response) {
            HttpStatusCode.OK
        } else {
            throw CategoryDeleteException()
        }
    }
}