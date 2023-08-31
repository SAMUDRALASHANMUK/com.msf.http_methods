package com.msf.database.table

import com.msf.util.appconstants.GlobalConstants.MAX_BODY_LENGTH
import com.msf.util.appconstants.GlobalConstants.MAX_TITLE_LENGTH
import org.jetbrains.exposed.sql.Table

object Articles : Table("Articles") {
    val id = integer("id").autoIncrement()
    val title = varchar("title", MAX_TITLE_LENGTH)
    val body = varchar("body", MAX_BODY_LENGTH)

    override val primaryKey = PrimaryKey(id)
}
