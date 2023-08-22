package com.msf.data.model

import kotlinx.serialization.Serializable

@Serializable
data class User(var userId: Int=1, var userName: String, var email: String)
