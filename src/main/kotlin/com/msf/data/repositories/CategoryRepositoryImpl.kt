package com.msf.data.repositories

import com.msf.dao.DatabaseFactory.dbQuery
import com.msf.data.methods.resultRowToCategory
import com.msf.data.model.Categorie
import com.msf.data.schemas.Categories
import com.msf.domain.interfaces.CategoryRepository
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class CategoryRepositoryImpl : CategoryRepository {
    override suspend fun addCategory(categoryName: String): Categorie? = dbQuery {
        // Insert the new category into the Categories table
        val insertStatement = Categories.insert {
            it[category_name] = categoryName
        }
        insertStatement.resultedValues?.map(::resultRowToCategory)?.singleOrNull()
    }


    override suspend fun getAllCategories(): List<Categorie> = dbQuery {
        Categories.selectAll().map(::resultRowToCategory)
    }

    override suspend fun getCategoryById(categoryId: Int): Categorie? = dbQuery {
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