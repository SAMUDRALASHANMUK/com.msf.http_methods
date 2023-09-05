package com.msf.entity

import com.msf.database.table.Articles
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

class ArticleEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<ArticleEntity>(Articles)

    val articleId by Articles.id
    val title by Articles.title
    val body by Articles.body


}
