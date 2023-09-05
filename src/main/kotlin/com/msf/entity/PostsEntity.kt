package com.msf.entity

import com.msf.database.table.Posts
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*


class PostsEntity(postId: EntityID<UUID>) : UUIDEntity(postId) {
    companion object : UUIDEntityClass<CategoriesEntity>(Posts)

    val userId by UsersEntity referencedOn Posts.user_id
    val title by Posts.title
    val content by Posts.content
}
