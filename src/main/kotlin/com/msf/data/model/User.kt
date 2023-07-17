package com.msf.data.model

import kotlinx.serialization.Serializable

@Serializable
data class User( var user_id: Int=1, val user_name: String, val email: String)
