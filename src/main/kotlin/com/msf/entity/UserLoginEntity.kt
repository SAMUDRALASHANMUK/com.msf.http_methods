package com.msf.entity

import com.msf.util.appconstants.GlobalConstants.MAX_PASSWORD_LENGTH
import com.msf.util.appconstants.GlobalConstants.MAX_USER_NAME_LENGTH
import org.jetbrains.exposed.sql.Table

object UserLoginEntity : Table("userlogin_table") {
    val userName = varchar("userName", MAX_USER_NAME_LENGTH)
    val password = varchar("password", MAX_PASSWORD_LENGTH)
    override val primaryKey = PrimaryKey(userName)
}
