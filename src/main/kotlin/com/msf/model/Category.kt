package com.msf.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

@Serializable
data class Category(@Contextual val categoryId: EntityID<@Contextual UUID>, val categoryName: String)

