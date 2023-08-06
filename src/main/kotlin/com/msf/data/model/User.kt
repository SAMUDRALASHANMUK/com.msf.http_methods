package com.msf.data.model

import kotlinx.serialization.Serializable

@Serializable
data class User(var user_id: Int=1, var user_name: String, var email: String)
