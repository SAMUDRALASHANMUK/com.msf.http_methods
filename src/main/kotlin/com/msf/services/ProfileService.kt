package com.msf.services

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import com.msf.exception.ProfileCreateException
import com.msf.exception.ProfileDeleteException
import com.msf.exception.ProfileNotFoundException
import com.msf.exception.ProfileUpdateException
import com.msf.model.Profile
import com.msf.repository.ProfileRepository
import io.ktor.http.*


class ProfileService : KoinComponent {
    private val profileRepository by inject<ProfileRepository>()
    suspend fun getAllProfiles(): List<Profile> {
        return profileRepository.getAllProfiles()
    }

    suspend fun getProfileById(id: Int): Profile {
        return profileRepository.getProfileById(id) ?: throw ProfileNotFoundException()
    }

    suspend fun createProfile(profile: Profile): Profile {
        profileRepository.getProfileByUserId(profile.userId)

        return profileRepository.createProfile(profile.userId, profile.profileData)
            ?: throw ProfileCreateException()
    }


    suspend fun updateUser(id: Int, profile: Profile): HttpStatusCode {
        val response = profileRepository.editProfile(id, profile.profileData)
        return if (response) {
            HttpStatusCode.OK
        } else {
            throw ProfileUpdateException()
        }
    }

    suspend fun deleteProfile(id: Int): HttpStatusCode {
        val response = profileRepository.deleteProfile(id)
        return if (response) {
            HttpStatusCode.OK
        } else {
            throw ProfileDeleteException()
        }

    }
}
