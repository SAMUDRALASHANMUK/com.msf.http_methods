package com.msf.repository

import com.msf.dao.PostCategoryDAO
import com.msf.config.DatabaseFactory.dbQuery
import com.msf.database.table.Categories
import com.msf.database.table.PostCategories
import com.msf.database.table.Posts
import com.msf.model.Category
import com.msf.model.Post
import com.msf.util.helperfunctions.resultRowToCategory
import com.msf.util.helperfunctions.resultRowToPost
import kotlinx.serialization.Contextual
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

class PostCategoriesRepository : PostCategoryDAO {

    override suspend fun getPostsForCategory(categoryId: UUID): List<Post> = dbQuery {
        return@dbQuery transaction {
            (Posts innerJoin PostCategories)
                .select(PostCategories.categoryId eq categoryId)
                .map(::resultRowToPost)
        }
    }

    override suspend fun getCategoriesForPost(postId: UUID): List<Category> = dbQuery {
        return@dbQuery transaction {
            (PostCategories innerJoin Categories)
                .select(PostCategories.id eq postId)
                .map(::resultRowToCategory)
        }
    }

    override suspend fun associatePostWithCategory(postId: EntityID<@Contextual UUID>, categoryId: EntityID<@Contextual UUID>): Boolean = dbQuery {

        PostCategories.insert {
            it[PostCategories.postId] = postId
            it[PostCategories.categoryId] = categoryId
        }
        true
    }
}
