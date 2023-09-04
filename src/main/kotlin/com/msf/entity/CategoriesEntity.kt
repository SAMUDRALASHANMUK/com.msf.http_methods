package com.msf.entity

import com.msf.database.table.Articles
import com.msf.database.table.Categories
import com.msf.util.appconstants.GlobalConstants.CATEGORY_ID
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class CategoriesEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<CategoriesEntity>(Categories)

    val categoryId by Categories.id
    val categoryName by Categories.category_name
}
