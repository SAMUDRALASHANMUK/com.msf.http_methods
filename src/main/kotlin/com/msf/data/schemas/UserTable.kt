package com.msf.data.schemas

import org.jetbrains.exposed.sql.Table

object Users : Table("users_table") {
    val user_id = integer("user_id").autoIncrement()
    val user_name = varchar("user_name", 100)
    val email = varchar("email", 100)
    override val primaryKey = PrimaryKey(user_id)
}