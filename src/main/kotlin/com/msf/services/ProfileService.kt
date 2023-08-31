package com.msf.services

import com.msf.config.status.ProfileCreateException
import com.msf.config.status.ProfileNotFoundException
import com.msf.model.Profile
import com.msf.repository.ProfileRepositoryImpl
import io.ktor.http.*


class ProfileService {
    private val profileRepositoryImpl = ProfileRepositoryImpl()
    suspend fun getAllProfiles(): List<Profile> {
        return profileRepositoryImpl.getAllProfiles()
    }

    suspend fun getProfileById(id: Int): Profile {
        return profileRepositoryImpl.getProfileById(id) ?: throw ProfileNotFoundException()
    }

    suspend fun createProfile(profile: Profile): Profile {
        profileRepositoryImpl.getProfileByUserId(profile.userId)

        return profileRepositoryImpl.createProfile(profile.userId, profile.profileData)
            ?: throw ProfileCreateException()
    }


    suspend fun updateUser(id: Int, profile: Profile): HttpStatusCode {
        val response = profileRepositoryImpl.editProfile(id, profile.profileData)
        return if (response) {
            HttpStatusCode.OK
        } else {
            throw ProfileNotFoundException()
        }
    }

    suspend fun deleteProfile(id: Int): HttpStatusCode {
        val response = profileRepositoryImpl.deleteProfile(id)
        return if (response) {
            HttpStatusCode.OK
        } else {
            throw ProfileNotFoundException()
        }

    }
}
