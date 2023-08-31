package com.msf.repository

import com.msf.config.DatabaseFactory.dbQuery
import com.msf.dao.CategoryDAO
import com.msf.data.schemas.Categories
import com.msf.model.Category
import com.msf.util.helperfunctions.resultRowToCategory
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.update
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class CategoryRepositoryImpl : CategoryDAO {
    override suspend fun addCategory(categoryName: String): Category? = dbQuery {
        // Insert the new category into the Categories table
        val insertStatement = Categories.insert {
            it[category_name] = categoryName
        }
        insertStatement.resultedValues?.map(::resultRowToCategory)?.singleOrNull()
    }


    override suspend fun getAllCategories(): List<Category> = dbQuery {
        Categories.selectAll().map(::resultRowToCategory)
    }

    override suspend fun getCategoryById(categoryId: Int): Category? = dbQuery {
        Categories
            .select { Categories.category_id eq categoryId }
            .map(::resultRowToCategory)
            .singleOrNull()
    }

    override suspend fun updateCategory(categoryId: Int, categoryName: String): Boolean = dbQuery {
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
