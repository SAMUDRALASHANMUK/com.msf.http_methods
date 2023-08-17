//package com.msf.data.schemas
//
//import org.jetbrains.exposed.sql.Table
//
//object Categories : Table("categories_table") {
//
//    val category_id = integer("category_id").autoIncrement()
//    val category_name = varchar("category_name", 50).uniqueIndex()
//    val post_id = integer("post_id") references (Posts.post_id)
//
//
//    override val primaryKey = PrimaryKey(category_id)
//}
package com.msf.data.schemas

import org.jetbrains.exposed.sql.Table

object Categories : Table("categories_table") {

    val category_id = integer("category_id").autoIncrement()
    val category_name = varchar("category_name", 50)


    override val primaryKey = PrimaryKey(category_id)
}