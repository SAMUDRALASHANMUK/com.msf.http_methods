package com.msf.repository

import com.msf.dao.PostCategoryDAO
import com.msf.config.DatabaseFactory.dbQuery
import com.msf.data.schemas.Categories
import com.msf.data.schemas.PostCategories
import com.msf.data.schemas.Posts
import com.msf.model.Category
import com.msf.model.Post
import com.msf.util.helperfunctions.resultPostCategory
import com.msf.util.helperfunctions.resultRowToCategory
import com.msf.util.helperfunctions.resultRowToPost
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class PostCategoriesRepositoryImpl : PostCategoryDAO {

    override suspend fun getPostsForCategory(categoryId: Int): List<Post> = dbQuery {
        return@dbQuery transaction {
            (Posts innerJoin PostCategories)
                .select(PostCategories.category_id eq categoryId)
                .map(::resultRowToPost)
        }
    }

    override suspend fun getCategoriesForPost(postId: Int): List<Category> = dbQuery {
        return@dbQuery transaction {
            (PostCategories innerJoin Categories)
                .select(PostCategories.post_id eq postId)
                .map(::resultRowToCategory)
        }
    }

    override suspend fun associatePostWithCategory(postId: Int, categoryId: Int): Boolean = dbQuery {

        val insertStatement = PostCategories.insert {
            it[post_id] = postId
            it[category_id] = categoryId
        }
        insertStatement.resultedValues?.map(::resultPostCategory)?.singleOrNull()
    }
}
