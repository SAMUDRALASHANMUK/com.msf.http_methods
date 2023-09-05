package com.msf.repository

import com.msf.config.DatabaseFactory.dbQuery
import com.msf.dao.CategoryDAO
import com.msf.database.table.Categories
import com.msf.model.Category
import com.msf.util.helperfunctions.resultRowToCategory
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.update
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.util.UUID


class CategoryRepository : CategoryDAO {
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

    override suspend fun getCategoryById(categoryId: UUID): Category? = dbQuery {
        Categories
            .select { Categories.id eq categoryId }
            .map(::resultRowToCategory)
            .singleOrNull()
    }

    override suspend fun updateCategory(categoryId: UUID, categoryName: String): Boolean = dbQuery {
        val updateRows = Categories.update({ Categories.id eq categoryId }) {
            it[id] = categoryId
            it[category_name] = categoryName
        }
        updateRows > 0
    }

    override suspend fun removeCategory(categoryId: UUID): Boolean = dbQuery {
        val deleteRows = Categories.deleteWhere { Categories.id eq categoryId }
        deleteRows > 0
    }
}
