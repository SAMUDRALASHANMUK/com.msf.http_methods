package com.msf.dao

import com.msf.model.Post
import kotlinx.serialization.Contextual
import org.jetbrains.exposed.dao.id.EntityID
import java.util.UUID


interface PostDAO {
    suspend fun createPost(userId: EntityID<@Contextual UUID>, title: String, content: String): Post?
    suspend fun getPostById(postId: UUID): Post?

    suspend fun getAllPosts(): List<Post>
    suspend fun editPost(postId: EntityID<@Contextual UUID>, newTitle: String, newContent: String): Boolean
    suspend fun deletePost(postId: UUID): Boolean
}
