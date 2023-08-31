package com.msf.services

import com.msf.config.status.PostCreateException
import com.msf.config.status.PostDeleteException
import com.msf.config.status.PostNotFoundException
import com.msf.model.Post
import com.msf.repository.PostRepositoryImpl
import io.ktor.http.*

class PostService {
    private val postRepositoryImpl = PostRepositoryImpl()
    suspend fun getAllPosts(): List<Post> {
        return postRepositoryImpl.getAllPosts()
    }

    suspend fun createPost(post: Post): Post {
        val post = postRepositoryImpl.createPost(post.userId, post.title, post.content)
        return post ?: throw PostCreateException()
    }

    suspend fun getPostById(id: Int): Post {
        val response = postRepositoryImpl.getPostById(id)
        return response ?: throw PostNotFoundException()
    }

    suspend fun editPost(post: Post): HttpStatusCode {
        val response = postRepositoryImpl.editPost(
            post.postId,
            post.title,
            post.content
        )
        return if (response) {
            HttpStatusCode.OK
        } else {
            throw PostNotFoundException()
        }
    }

    suspend fun deletePost(id: Int): HttpStatusCode {
        val response = postRepositoryImpl.deletePost(id)
        return if (response) {
            HttpStatusCode.OK
        } else {
            throw PostDeleteException()
        }
    }
}