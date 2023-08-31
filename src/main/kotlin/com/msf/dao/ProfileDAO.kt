package com.msf.dao

import com.msf.data.model.Profile

interface ProfileDAO {
    suspend fun createProfile(userId: Int, profileData: String): Profile?
    suspend fun getProfileById(profileId: Int): Profile?

    suspend fun getAllProfiles(): List<Profile>

    suspend fun getProfileByUserId(userId: Int):Profile?
    suspend fun editProfile(profileId: Int, newProfileData: String): Boolean
    suspend fun deleteProfile(profileId: Int): Boolean

}
