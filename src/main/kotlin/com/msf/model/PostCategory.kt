package com.msf.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

@Serializable
data class PostCategory(@Contextual val postId: EntityID<@Contextual UUID>, @Contextual val categoryId: EntityID<@Contextual UUID>)
