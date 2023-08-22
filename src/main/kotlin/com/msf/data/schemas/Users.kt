package com.msf.data.schemas

import com.msf.util.appconstants.GlobalConstants.MAX_USER_NAME_LENGTH
import com.msf.util.appconstants.GlobalConstants.MAX_EMAIL_LENGTH
import org.jetbrains.exposed.sql.Table

object Users : Table("users_table") {
    val user_id = integer("user_id").autoIncrement()
    val user_name = varchar("user_name", MAX_USER_NAME_LENGTH)
    val email = varchar("email", MAX_EMAIL_LENGTH)
    override val primaryKey = PrimaryKey(user_id)
}
