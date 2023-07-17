package com.msf.data.schemas

import org.jetbrains.exposed.sql.Table

object Articles : Table("Articles") {
    val id = integer("id").autoIncrement()
    val title = varchar("title", 100)
    val body = varchar("body", 100)
    override val primaryKey = PrimaryKey(id)
}