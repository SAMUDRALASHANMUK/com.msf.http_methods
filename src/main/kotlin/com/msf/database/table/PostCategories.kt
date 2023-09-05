package com.msf.database.table

import com.msf.database.table.Categories.primaryKey
import com.msf.database.table.Posts.primaryKey
import com.msf.util.appconstants.GlobalConstants.CATEGORY_ID
import com.msf.util.appconstants.GlobalConstants.POST_CATEGORIES_TABLE_NAME
import com.msf.util.appconstants.GlobalConstants.POST_ID
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.UUIDTable
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import java.util.*


object PostCategories : UUIDTable(POST_CATEGORIES_TABLE_NAME) {
    val postId = reference(POST_ID, Posts, onDelete = ReferenceOption.CASCADE)
    val categoryId = reference(CATEGORY_ID, Categories, onDelete = ReferenceOption.CASCADE)

}

