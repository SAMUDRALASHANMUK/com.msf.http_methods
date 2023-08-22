package com.msf.data.repositories

import com.msf.data.DatabaseFactory.dbQuery
import com.msf.data.methods.resultPostCategory
import com.msf.data.methods.resultRowToCategory
import com.msf.data.methods.resultRowToPost
import com.msf.data.model.Category
import com.msf.data.model.Post
import com.msf.data.schemas.Categories
import com.msf.data.schemas.PostCategories
import com.msf.data.schemas.Posts
import com.msf.domain.interfaces.PostCategoriesRepository
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class PostCategoriesRepositoryImpl : PostCategoriesRepository {

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

    override suspend fun associatePostWithCategory(postId: Int, categoryId: Int): Unit = dbQuery {

        val insertStatement = PostCategories.insert {
            it[post_id] = postId
            it[category_id] = categoryId
        }
        insertStatement.resultedValues?.map(::resultPostCategory)?.singleOrNull()
    }
}
