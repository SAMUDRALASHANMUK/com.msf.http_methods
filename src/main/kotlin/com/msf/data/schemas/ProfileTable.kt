package com.msf.data.schemas

import org.jetbrains.exposed.sql.Table

object Profiles : Table("profiles_table") {
    val profile_id = integer("profile_id").autoIncrement()
    val user_id = integer("user_id").references(Users.user_id).uniqueIndex()
    val profile_data = varchar("profile_data", 100)
    override val primaryKey = PrimaryKey(profile_id)
}