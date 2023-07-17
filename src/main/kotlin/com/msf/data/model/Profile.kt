package com.msf.data.model

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table
@Serializable
data class Profile(val profile_id: Int=1, val user_id: Int, val profile_data: String)

