package com.msf.entity

import com.msf.database.table.PostCategories
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class PostCategoriesEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<PostCategoriesEntity>(PostCategories)

    val postId by PostCategories.postId
    val categoryId by PostCategories.categoryId
}

