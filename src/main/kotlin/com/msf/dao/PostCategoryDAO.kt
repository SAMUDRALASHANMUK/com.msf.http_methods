package com.msf.dao

import com.msf.model.Category
import com.msf.model.Post
import kotlinx.serialization.Contextual
import org.jetbrains.exposed.dao.id.EntityID
import java.util.UUID

interface PostCategoryDAO {

    suspend fun getPostsForCategory(categoryId: UUID): List<Post>
    suspend fun getCategoriesForPost(postId: UUID): List<Category>
    suspend fun associatePostWithCategory(postId: EntityID<@Contextual UUID>, categoryId: EntityID<@Contextual UUID>):Boolean

}
