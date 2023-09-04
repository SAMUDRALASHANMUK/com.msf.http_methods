package com.msf.database.table

import com.msf.util.appconstants.GlobalConstants.MAX_CONTENT_LENGTH
import com.msf.util.appconstants.GlobalConstants.MAX_TITLE_LENGTH
import com.msf.util.appconstants.GlobalConstants.POSTS_TABLE_NAME
import com.msf.util.appconstants.GlobalConstants.POST_CONTENT
import com.msf.util.appconstants.GlobalConstants.POST_ID
import com.msf.util.appconstants.GlobalConstants.POST_TITLE
import com.msf.util.appconstants.GlobalConstants.USER_ID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Table

object Posts : UUIDTable(POSTS_TABLE_NAME) {

    val user_id = integer(USER_ID) references Users.user_id
    val title = varchar(POST_TITLE, MAX_TITLE_LENGTH)
    val content = varchar(POST_CONTENT, MAX_CONTENT_LENGTH)

}
