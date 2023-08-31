package com.msf.dao

import com.msf.data.model.Category

interface CategoryDAO {


    // Add a new category
    suspend fun addCategory(categoryName: String): Category?

    // Get all categories
    suspend fun getAllCategories(): List<Category>

    // Get a category by its ID
    suspend fun getCategoryById(categoryId: Int): Category?

    // Update an existing category
    suspend fun updateCategory(categoryId: Int, categoryName: String): Boolean

    // Remove a category
    suspend fun removeCategory(categoryId: Int): Boolean
}
