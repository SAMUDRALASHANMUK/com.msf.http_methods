package com.msf.data.repositories

import com.msf.data.DatabaseFactory.dbQuery
import com.msf.data.methods.resultRowToPost
import com.msf.data.model.Post
import com.msf.data.schemas.Posts
import com.msf.data.schemas.Users
import com.msf.domain.exceptions.UserNotFoundException
import com.msf.domain.interfaces.PostRepository
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.update
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class PostRepositoryImpl : PostRepository {
    override suspend fun createPost(userId: Int, title: String, content: String): Post? = dbQuery {
        // Check if the user with the given userId exists
        Users.select { Users.user_id eq userId }
            .singleOrNull() ?: throw UserNotFoundException()

        // The user exists, proceed with creating the post
        val insertStatement = Posts.insert {

            it[user_id] = userId
            it[Posts.title] = title
            it[Posts.content] = content
        }

        insertStatement.resultedValues?.map(::resultRowToPost)?.singleOrNull()
    }


    override suspend fun getPostById(postId: Int): Post? = dbQuery {
        Posts
            .select(Posts.post_id eq postId)
            .map(::resultRowToPost)
            .singleOrNull()
    }

    override suspend fun getAllPosts(): List<Post> = dbQuery {
        Posts.selectAll().map(::resultRowToPost)
    }

    override suspend fun editPost(postId: Int, newTitle: String, newContent: String): Boolean =
        dbQuery {
            val updateRows = Posts.update({ Posts.post_id eq postId }) {
                it[title] = newTitle
                it[content] = newContent
            }
            updateRows > 0
        }

    override suspend fun deletePost(postId: Int): Boolean = dbQuery {
        val deletedRows = Users.deleteWhere { Posts.post_id eq postId }
        deletedRows > 0
    }


}
