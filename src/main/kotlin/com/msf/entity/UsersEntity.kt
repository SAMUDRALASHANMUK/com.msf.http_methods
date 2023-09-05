package com.msf.entity

import com.msf.database.table.Users
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*


class UsersEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<CategoriesEntity>(Users)

    val userName by Users.user_name
    val email by Users.email
}