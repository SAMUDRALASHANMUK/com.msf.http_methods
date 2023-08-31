package com.msf.model

import kotlinx.serialization.Serializable

@Serializable
data class Article(val id: Int, val title: String, val body: String)

