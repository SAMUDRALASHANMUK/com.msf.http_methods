package com.msf.data.repositories

import com.msf.dao.DatabaseFactory.dbQuery
import com.msf.data.model.Categorie
import com.msf.data.model.Post
import com.msf.data.schemas.Categories
import com.msf.data.schemas.PostCategories
import com.msf.data.schemas.Posts
import com.msf.domain.interfaces.PostCategoriesRepository
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class PostCategoriesRepositoryImpl : PostCategoriesRepository {

    override suspend fun getPostsForCategory(categoryId: Int): List<Post> = dbQuery {
        return@dbQuery transaction {
            (PostCategories innerJoin Posts)
                .select { PostCategories.category_id eq categoryId }
                .map {
                    Post(
                        it[Posts.post_id],
                        it[Posts.user_id],
                        it[Posts.title],
                        it[Posts.content],
                        it[Posts.category_id]
                    )
                }
        }
    }

    override suspend fun getCategoriesForPost(postId: Int): List<Categorie> = dbQuery {
        return@dbQuery transaction {
            (PostCategories innerJoin Categories)
                .select { PostCategories.post_id eq postId }
                .map { Categorie(it[Categories.category_id], it[Categories.category_name], it[Categories.post_id]) }
        }
    }

    override suspend fun associatePostWithCategory(postId: Int, categoryId: Int) {
        transaction {
            PostCategories.insert {
                it[PostCategories.post_id] = postId
                it[PostCategories.category_id] = categoryId
            }
        }
    }
}