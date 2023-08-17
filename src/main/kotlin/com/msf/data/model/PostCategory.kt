package com.msf.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PostCategory(val post_id: Int, val category_id: Int)
