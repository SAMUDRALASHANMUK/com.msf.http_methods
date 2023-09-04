package com.msf.database.table

import com.msf.util.appconstants.GlobalConstants.CATEGORY_ID
import com.msf.util.appconstants.GlobalConstants.CATEGORY_NAME
import com.msf.util.appconstants.GlobalConstants.CATEGORY_TABLE_NAME
import com.msf.util.appconstants.GlobalConstants.MAX_CATEGORY_LENGTH
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Table

object Categories : UUIDTable(CATEGORY_TABLE_NAME) {

    val category_name = varchar(CATEGORY_NAME, MAX_CATEGORY_LENGTH)

}
