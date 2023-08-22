package com.msf.data.model

import kotlinx.serialization.Serializable

@Serializable
data class PostClientTest(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)
