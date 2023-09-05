package com.msf.dao

import com.msf.model.Category
import java.util.UUID

interface CategoryDAO {


    // Add a new category
    suspend fun addCategory(categoryName: String): Category?

    // Get all categories
    suspend fun getAllCategories(): List<Category>

    // Get a category by its ID
    suspend fun getCategoryById(categoryId: UUID): Category?

    // Update an existing category
    suspend fun updateCategory(categoryId: UUID, categoryName: String): Boolean

    // Remove a category
    suspend fun removeCategory(categoryId: UUID): Boolean
}
