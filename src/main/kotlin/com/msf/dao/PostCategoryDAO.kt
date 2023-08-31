package com.msf.dao

import com.msf.model.Category
import com.msf.model.Post

interface PostCategoryDAO {

    suspend fun getPostsForCategory(categoryId: Int): List<Post>
    suspend fun getCategoriesForPost(postId: Int): List<Category>
    suspend fun associatePostWithCategory(postId: Int, categoryId: Int):Boolean

}
