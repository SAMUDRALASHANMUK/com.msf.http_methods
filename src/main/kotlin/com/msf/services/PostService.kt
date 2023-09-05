package com.msf.services

import com.msf.exception.PostCreateException
import com.msf.exception.PostDeleteException
import com.msf.exception.PostNotFoundException
import com.msf.exception.PostUpdateException
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import com.msf.model.Post
import com.msf.repository.PostRepository
import io.ktor.http.*
import java.util.UUID

class PostService : KoinComponent {
    private val postRepositoryImpl by inject<PostRepository>()
    suspend fun getAllPosts(): List<Post> {
        return postRepositoryImpl.getAllPosts()
    }

    suspend fun createPost(post: Post): Post {
        val response = postRepositoryImpl.createPost(post.userId, post.title, post.content)
        return response ?: throw PostCreateException()
    }

    suspend fun getPostById(id: UUID): Post {
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
            throw PostUpdateException()
        }
    }

    suspend fun deletePost(id: UUID): HttpStatusCode {
        val response = postRepositoryImpl.deletePost(id)
        return if (response) {
            HttpStatusCode.OK
        } else {
            throw PostDeleteException()
        }
    }
}
