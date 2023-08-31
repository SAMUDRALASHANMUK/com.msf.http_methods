package com.msf.model

import kotlinx.serialization.Serializable

@Serializable
data class PostCategory(val postId: Int, val categoryId: Int)
