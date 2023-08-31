package com.msf.model

import kotlinx.serialization.Serializable

@Serializable
data class Profile(val profileId: Int=1, val userId: Int, val profileData: String)

