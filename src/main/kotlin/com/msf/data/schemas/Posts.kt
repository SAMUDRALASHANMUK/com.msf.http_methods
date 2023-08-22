package com.msf.data.schemas

import com.msf.util.appconstants.GlobalConstants.MAX_CONTENT_LENGTH
import com.msf.util.appconstants.GlobalConstants.MAX_TITLE_LENGTH
import org.jetbrains.exposed.sql.Table

object Posts : Table("posts_table") {
    val post_id = integer("post_id").autoIncrement()
    val user_id = integer("user_id") references Users.user_id
    val title = varchar("title", MAX_TITLE_LENGTH)
    val content = varchar("content", MAX_CONTENT_LENGTH)

    override val primaryKey = PrimaryKey(post_id)
}
