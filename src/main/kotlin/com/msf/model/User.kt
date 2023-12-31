package com.msf.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.dao.id.EntityID
import java.util.UUID

@Serializable
data class User(
    var id: String,
    var userName: String,
    var email: String
)
@Serializable
data class UserInput(
    var userName: String,
    var email: String
)