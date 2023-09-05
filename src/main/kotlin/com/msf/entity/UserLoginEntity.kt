package com.msf.entity

import com.msf.database.table.UserLoginTable
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*


class UserLoginEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<UserLoginEntity>(UserLoginTable)

    val userName by UserLoginTable.userName
    val password by UserLoginTable.password

}