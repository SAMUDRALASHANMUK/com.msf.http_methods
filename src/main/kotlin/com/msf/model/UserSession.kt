package com.msf.model

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class UserSession(val sessionId: String = UUID.randomUUID().toString(), val userName: String, val password: String)
