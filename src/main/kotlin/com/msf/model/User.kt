package com.msf.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.EntityID
import java.util.UUID

@Serializable
data class User(@Contextual var id: EntityID<@Contextual UUID>, var userName: String, var email: String)
