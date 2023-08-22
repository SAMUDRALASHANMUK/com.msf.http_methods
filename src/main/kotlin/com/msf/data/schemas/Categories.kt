package com.msf.data.schemas

import com.msf.util.appconstants.GlobalConstants.MAX_CATEGORY_LENGTH
import org.jetbrains.exposed.sql.Table

object Categories : Table("categories_table") {

    val category_id = integer("category_id").autoIncrement()
    val category_name = varchar("category_name", MAX_CATEGORY_LENGTH)


    override val primaryKey = PrimaryKey(category_id)
}
