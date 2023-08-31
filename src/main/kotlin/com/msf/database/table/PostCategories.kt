package com.msf.database.table

import org.jetbrains.exposed.sql.Table

object PostCategories : Table("post_categories") {

    val post_id = integer("post_id") references (Posts.post_id)
    val category_id = integer("category_id") references (Categories.category_id)

    override val primaryKey = PrimaryKey(post_id, category_id)
}
