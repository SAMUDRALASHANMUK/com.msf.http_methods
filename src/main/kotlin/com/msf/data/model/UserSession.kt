package com.msf.data.model

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class UserSession(val sessionId: String = UUID.randomUUID().toString(), val userName: String, val password: String)