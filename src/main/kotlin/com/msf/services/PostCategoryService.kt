package com.msf.services

import com.msf.config.status.PostCategoryCreateException
import com.msf.config.status.PostCategoryException
import com.msf.model.Category
import com.msf.model.Post
import com.msf.model.PostCategory
import com.msf.repository.PostCategoriesRepositoryImpl
import io.ktor.http.*

class PostCategoryService {
    private val postCategoriesRepository = PostCategoriesRepositoryImpl()
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
