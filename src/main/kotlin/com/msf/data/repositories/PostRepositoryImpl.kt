package com.msf.data.repositories

import com.msf.dao.DatabaseFactory.dbQuery
import com.msf.data.methods.resultRowToPost
import com.msf.data.model.Post
import com.msf.data.schemas.PostCategories
import com.msf.data.schemas.Posts
import com.msf.data.schemas.Users
import com.msf.domain.exceptions.UserNotFoundException
import com.msf.domain.interfaces.PostRepository
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class PostRepositoryImpl : PostRepository {
    override suspend fun createPost(userId: Int, title: String, content: String, categoryId: Int): Post? = dbQuery {
        // Check if the user with the given userId exists
        Users.select { Users.user_id eq userId }
            .singleOrNull() ?: throw UserNotFoundException("User with ID $userId not found")

        // The user exists, proceed with creating the post
        val insertStatement = Posts.insert {

            it[user_id] = userId
            it[Posts.title] = title
            it[Posts.content] = content
            it[category_id] = categoryId
        }
        val postId = insertStatement.resultedValues?.singleOrNull()?.get(Posts.post_id)

        // Insert association between post and category into the PostCategories table
        if (postId != null) {
            PostCategories.insert {
                it[post_id] = postId
                it[category_id] = categoryId
            }
        }

        // Fetch the complete post with associated category
        postId?.let { postId ->
            val result = Posts.select { Posts.post_id eq postId }.singleOrNull()
            result?.let(::resultRowToPost)
        }
    }


    override suspend fun getPostById(postId: Int): Post? = dbQuery {
        Posts
            .selectAll()
            .map(::resultRowToPost)
            .singleOrNull()
    }

    override suspend fun getAllPosts(): List<Post> = dbQuery {
        Posts.selectAll().map(::resultRowToPost)
    }

    override suspend fun editPost(postId: Int, newTitle: String, newContent: String, categoryId: Int): Boolean =
        dbQuery {
            val updateRows = Posts.update({ Posts.post_id eq postId }) {
                it[title] = newTitle
                it[content] = newContent
                it[category_id] = categoryId

            }
            updateRows > 0
        }

    override suspend fun deletePost(postId: Int): Boolean = dbQuery {
        val deletedRows = Users.deleteWhere { Posts.post_id eq postId }
        deletedRows > 0
    }


}