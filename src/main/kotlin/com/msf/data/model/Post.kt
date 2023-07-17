package com.msf.data.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
@Serializable
data class Post(val post_id: Int=1, val user_id: Int, val title: String, val content: String)


