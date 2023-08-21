package com.msf.data.schemas

import org.jetbrains.exposed.sql.Table

object UserLoginTable : Table("userlogin_table") {
    val userName = varchar("userName",100)
    val password = varchar("password", 20)
    override val primaryKey = PrimaryKey(userName)
}
