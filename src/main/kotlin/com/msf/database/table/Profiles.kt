package com.msf.database.table

import com.msf.util.appconstants.GlobalConstants.MAX_PROFILE_DATA_LENGTH
import com.msf.util.appconstants.GlobalConstants.PROFILE_DATA
import com.msf.util.appconstants.GlobalConstants.PROFILE_TABLE_NAME
import com.msf.util.appconstants.GlobalConstants.USER_ID
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ReferenceOption
import java.util.*

object Profiles : UUIDTable(PROFILE_TABLE_NAME) {
    val user_id: Column<EntityID<UUID>> = reference(USER_ID, Users, onDelete = ReferenceOption.CASCADE).uniqueIndex()
    val profile_data = varchar(PROFILE_DATA, MAX_PROFILE_DATA_LENGTH)
}
