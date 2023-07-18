package com.msf.domain.interfaces

import com.msf.data.model.Categorie

interface CategoryRepository {


    // Add a new category
    suspend fun addCategory(categoryName: String, postId: Int): Categorie?

    // Get all categories
    suspend fun getAllCategories(): List<Categorie>

    // Get a category by its ID
    suspend fun getCategoryById(categoryId: Int): Categorie?

    // Update an existing category
    suspend fun updateCategory(categoryId: Int, categoryName: String, postId: Int): Boolean

    // Remove a category
    suspend fun removeCategory(categoryId: Int): Boolean
}