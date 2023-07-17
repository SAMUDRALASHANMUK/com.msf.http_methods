package com.msf.data.schemas

import org.jetbrains.exposed.sql.Table

object Posts : Table("posts_table") {
    val post_id = integer("post_id").autoIncrement()
    val user_id = integer("user_id") references Users.user_id
    val title = varchar("title", 100)
    val content = varchar("content", 200)
}