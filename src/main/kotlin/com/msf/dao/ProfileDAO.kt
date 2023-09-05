package com.msf.dao

import com.msf.model.Profile
import kotlinx.serialization.Contextual
import org.jetbrains.exposed.dao.id.EntityID
import java.util.UUID

interface ProfileDAO {
    suspend fun createProfile(userId: EntityID<@Contextual UUID>, profileData: String): Profile?
    suspend fun getProfileById(profileId: UUID): Profile?

    suspend fun getAllProfiles(): List<Profile>

    suspend fun getProfileByUserId(userId: EntityID<@Contextual UUID>):Profile?
    suspend fun editProfile(profileId: UUID, newProfileData: String): Boolean
    suspend fun deleteProfile(profileId: UUID): Boolean

}
