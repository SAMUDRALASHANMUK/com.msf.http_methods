package com.msf.database.table


import com.msf.util.appconstants.GlobalConstants.POST_CATEGORIES_TABLE_NAME

import org.jetbrains.exposed.dao.id.UUIDTable


object PostCategories : UUIDTable(POST_CATEGORIES_TABLE_NAME) {
    val postId = reference("postId", Posts)
    val categoryId = reference("categoryId", Categories)
}

