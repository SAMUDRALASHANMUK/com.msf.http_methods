package com.msf.database.table

import com.msf.util.appconstants.GlobalConstants.MAX_PASSWORD_LENGTH
import com.msf.util.appconstants.GlobalConstants.MAX_USER_NAME_LENGTH
import com.msf.util.appconstants.GlobalConstants.USER_LOGIN_NAME
import com.msf.util.appconstants.GlobalConstants.USER_LOGIN_PASSWORD
import com.msf.util.appconstants.GlobalConstants.USER_LOGIN_TABLE_NAME
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Table

object UserLoginTable : UUIDTable(USER_LOGIN_TABLE_NAME) {
    val userName = varchar(USER_LOGIN_NAME, MAX_USER_NAME_LENGTH)
    val password = varchar(USER_LOGIN_PASSWORD, MAX_PASSWORD_LENGTH)
}
