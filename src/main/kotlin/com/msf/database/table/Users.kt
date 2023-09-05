package com.msf.database.table

import com.msf.util.appconstants.GlobalConstants.MAX_USER_NAME_LENGTH
import com.msf.util.appconstants.GlobalConstants.MAX_EMAIL_LENGTH
import com.msf.util.appconstants.GlobalConstants.USER_EMAIL
import com.msf.util.appconstants.GlobalConstants.USER_ID
import com.msf.util.appconstants.GlobalConstants.USER_NAME
import com.msf.util.appconstants.GlobalConstants.USER_TABLE_NAME
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Table

object Users : UUIDTable(USER_TABLE_NAME) {
    val user_name = varchar(USER_NAME, MAX_USER_NAME_LENGTH)
    val email = varchar(USER_EMAIL, MAX_EMAIL_LENGTH)
}
