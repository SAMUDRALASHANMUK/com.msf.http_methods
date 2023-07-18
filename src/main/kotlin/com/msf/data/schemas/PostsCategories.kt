package com.msf.data.schemas

import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table

object PostCategories : Table("post_categories") {

    val post_id = integer("post_id")
    val category_id = integer("category_id")

    override val primaryKey = PrimaryKey(post_id, category_id)
}
