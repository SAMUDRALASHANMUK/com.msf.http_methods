package com.msf.model

import kotlinx.serialization.Serializable

@Serializable
data class UserLogin(val userName: String, val password: String)

