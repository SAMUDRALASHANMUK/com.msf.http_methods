package com.msf.data.repositories

import com.msf.dao.DatabaseFactory.dbQuery
import com.msf.data.methods.resultRowToCategorie
import com.msf.data.model.Categorie
import com.msf.data.schemas.Categories
import com.msf.data.schemas.PostCategories
import com.msf.domain.interfaces.CategoryRepository
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class CategoryRepositoryImpl : CategoryRepository {
    override suspend fun addCategory( categoryName: String, postId: Int): Categorie? = dbQuery {
        // Insert the new category into the Categories table
        val insertStatement = Categories.insert {

            it[category_name] = categoryName
            it[post_id] = postId
        }
        val categoryId = insertStatement.resultedValues?.singleOrNull()?.get(Categories.category_id)

        // Insert association between post and category into the PostCategories table
        if (categoryId != null) {
            PostCategories.insert {
                it[post_id] = postId
                it[category_id] = categoryId
            }

        }
        // Fetch the complete post with associated category
        categoryId?.let { categoryId ->
            val result = Categories.select { Categories.category_id eq categoryId }.singleOrNull()
            result?.let(::resultRowToCategorie)
        }
    }


    override suspend fun getAllCategories(): List<Categorie> = dbQuery {
        Categories.selectAll().map(::resultRowToCategorie)
    }

    override suspend fun getCategoryById(categoryId: Int): Categorie? = dbQuery {
        Categories
            .select { Categories.category_id eq categoryId }
            .map(::resultRowToCategorie)
            .singleOrNull()
    }

    override suspend fun updateCategory(categoryId: Int, categoryName: String, postId: Int): Boolean = dbQuery {
        val updateRows = Categories.update({ Categories.category_id eq categoryId }) {
            it[category_id] = categoryId
            it[category_name] = categoryName

        }
        updateRows > 0
    }

    override suspend fun removeCategory(categoryId: Int): Boolean = dbQuery {
        val deleteRows = Categories.deleteWhere { Categories.category_id eq categoryId }
        deleteRows > 0
    }
}