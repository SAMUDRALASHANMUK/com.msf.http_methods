package com.msf.domain.interfaces

import com.msf.data.model.Post


interface PostRepository {
    suspend fun createPost(userId: Int, title: String, content: String, categoryId: Int): Post?
    suspend fun getPostById(postId: Int): Post?

    suspend fun getAllPosts(): List<Post>
    suspend fun editPost(postId: Int, newTitle: String, newContent: String, categoryId: Int): Boolean
    suspend fun deletePost(postId: Int): Boolean
}