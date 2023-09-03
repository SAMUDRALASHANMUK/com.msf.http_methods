package com.msf.entity

import com.msf.util.appconstants.GlobalConstants.MAX_PROFILE_DATA_LENGTH
import org.jetbrains.exposed.sql.Table

object ProfilesEntity : Table("profiles_table") {
    val profile_id = integer("profile_id").autoIncrement()
    val user_id = integer("user_id").references(UsersEntity.user_id).uniqueIndex()
    val profile_data = varchar("profile_data", MAX_PROFILE_DATA_LENGTH)
    override val primaryKey = PrimaryKey(profile_id)
}
