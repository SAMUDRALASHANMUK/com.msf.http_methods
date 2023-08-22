package com.msf.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Post(val postId: Int = 0, val userId: Int, val title: String, val content: String)

