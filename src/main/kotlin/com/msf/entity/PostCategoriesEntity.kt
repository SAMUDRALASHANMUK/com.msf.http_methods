package com.msf.entity

import org.jetbrains.exposed.sql.Table

object PostCategoriesEntity : Table("post_categories") {

    val post_id = integer("post_id") references (PostsEntity.post_id)
    val category_id = integer("category_id") references (CategoriesEntity.category_id)

    override val primaryKey = PrimaryKey(post_id, category_id)
}
