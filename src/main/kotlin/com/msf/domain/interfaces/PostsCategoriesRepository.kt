package com.msf.domain.interfaces

import com.msf.data.model.Categorie
import com.msf.data.model.Post

interface PostCategoriesRepository {

    suspend fun getPostsForCategory(categoryId: Int): List<Post>
    suspend fun getCategoriesForPost(postId: Int): List<Categorie>

}
