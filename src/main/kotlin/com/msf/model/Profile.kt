package com.msf.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.EntityID
import java.util.*

@Serializable
data class Profile(
    @Contextual val profileId: EntityID<@Contextual UUID>,
    @Contextual val userId: EntityID<@Contextual UUID>,
    val profileData: String
)
