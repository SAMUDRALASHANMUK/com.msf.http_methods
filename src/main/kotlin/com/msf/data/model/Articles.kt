package com.msf.data.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Article(val id: Int, val title: String, val body: String)

