package com.msf.database.table

import com.msf.util.appconstants.GlobalConstants.ARTICLE_BODY
import com.msf.util.appconstants.GlobalConstants.ARTICLE_TABLE_NAME
import com.msf.util.appconstants.GlobalConstants.ARTICLE_TITLE
import com.msf.util.appconstants.GlobalConstants.MAX_BODY_LENGTH
import com.msf.util.appconstants.GlobalConstants.MAX_TITLE_LENGTH
import org.jetbrains.exposed.dao.id.UUIDTable

object Articles : UUIDTable(ARTICLE_TABLE_NAME) {

    val title = varchar(ARTICLE_TITLE, MAX_TITLE_LENGTH)
    val body = varchar(ARTICLE_BODY, MAX_BODY_LENGTH)
}

