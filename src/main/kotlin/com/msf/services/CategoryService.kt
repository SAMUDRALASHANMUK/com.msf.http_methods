package com.msf.services

import com.msf.exception.CategoryCreateException
import com.msf.exception.CategoryDeleteException
import com.msf.exception.CategoryNotFoundException
import com.msf.exception.CategoryUpdateException
import com.msf.model.Category
import com.msf.repository.CategoryRepository
import io.ktor.http.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CategoryService : KoinComponent {
    private val categoryRepository by inject<CategoryRepository>()
    suspend fun getAllCategories(): List<Category> {
        return categoryRepository.getAllCategories()
    }

    suspend fun getCategoryById(id: Int): Category {
        val response = categoryRepository.getCategoryById(id)
        return response ?: throw CategoryNotFoundException()
    }

    suspend fun createCategory(category: Category): Category {
        val response = categoryRepository.addCategory(category.categoryName)
        return response ?: throw CategoryCreateException()
    }

    suspend fun updateCategory(id: Int, category: Category): HttpStatusCode {
        val response = categoryRepository.updateCategory(categoryId = id, category.categoryName)
        return if (response) {
            HttpStatusCode.OK
        } else {
            throw CategoryUpdateException()
        }
    }

    suspend fun deleteCategory(id: Int): HttpStatusCode {
        val response = categoryRepository.removeCategory(id)
        return if (response) {
            HttpStatusCode.OK
        } else {
            throw CategoryDeleteException()
        }
    }
}
