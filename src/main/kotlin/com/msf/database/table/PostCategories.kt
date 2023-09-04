package com.msf.database.table

import com.msf.util.appconstants.GlobalConstants.CATEGORY_ID
import com.msf.util.appconstants.GlobalConstants.POST_CATEGORIES_TABLE_NAME
import com.msf.util.appconstants.GlobalConstants.POST_ID
import org.jetbrains.exposed.dao.id.UUIDTable

object PostCategories : UUIDTable(POST_CATEGORIES_TABLE_NAME) {
    val postId = reference(POST_ID, Posts)
    val categoryId = reference(CATEGORY_ID, Categories)

}
