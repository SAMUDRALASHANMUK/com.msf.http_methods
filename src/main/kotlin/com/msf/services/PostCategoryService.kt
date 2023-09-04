package com.msf.services

import com.msf.exception.PostCategoryCreateException
import com.msf.exception.PostCategoryException
import com.msf.model.Category
import com.msf.model.Post
import com.msf.model.PostCategory
import com.msf.repository.PostCategoriesRepository
import io.ktor.http.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PostCategoryService : KoinComponent {

    private val postCategoriesRepository by inject<PostCategoriesRepository>()
    suspend fun createPostCategory(postCategory: PostCategory): HttpStatusCode {
        val response = postCategoriesRepository.associatePostWithCategory(postCategory.postId, postCategory.categoryId)
        return if (response) {
            HttpStatusCode.Created
        } else {
            throw PostCategoryCreateException()
        }
    }

    suspend fun getCategoriesForPost(postId: Int): List<Category> {
        val associatedCategories = postCategoriesRepository.getCategoriesForPost(postId)
        return associatedCategories.ifEmpty {
            throw PostCategoryException()
        }
    }

    suspend fun getPostsForCategory(categoryId: Int): List<Post> {
        val associatedPosts = postCategoriesRepository.getPostsForCategory(categoryId)
        return associatedPosts.ifEmpty {
            throw PostCategoryException()
        }
    }
}

