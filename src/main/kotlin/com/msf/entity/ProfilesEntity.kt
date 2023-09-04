package com.msf.entity

import com.msf.database.table.Profiles
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*


class ProfilesEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<CategoriesEntity>(Profiles)

    val profileId by Profiles.id
    val userId by Profiles.user_id
    val profileData by Profiles.profile_data

}
