package com.msf.database.table

import com.msf.util.appconstants.GlobalConstants.MAX_PROFILE_DATA_LENGTH
import com.msf.util.appconstants.GlobalConstants.PROFILE_DATA
import com.msf.util.appconstants.GlobalConstants.PROFILE_ID
import com.msf.util.appconstants.GlobalConstants.PROFILE_TABLE_NAME
import com.msf.util.appconstants.GlobalConstants.USER_ID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Table

object Profiles : UUIDTable(PROFILE_TABLE_NAME) {
    val user_id = integer(USER_ID).references(Users.user_id).uniqueIndex()
    val profile_data = varchar(PROFILE_DATA, MAX_PROFILE_DATA_LENGTH)
}
