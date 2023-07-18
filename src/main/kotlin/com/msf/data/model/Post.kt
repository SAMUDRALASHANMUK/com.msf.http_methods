package com.msf.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Post(val post_id: Int = 0, val user_id: Int, val title: String, val content: String, val category_id: Int)



