package com.msf.domain.interfaces

import com.msf.data.model.Category
import com.msf.data.model.Post

interface PostCategoriesRepository {

    suspend fun getPostsForCategory(categoryId: Int): List<Post>
    suspend fun getCategoriesForPost(postId: Int): List<Category>
    suspend fun associatePostWithCategory(postId: Int, categoryId: Int)

}
