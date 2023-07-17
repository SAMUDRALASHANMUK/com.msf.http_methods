package com.msf.data.repositories

import com.msf.dao.DatabaseFactory.dbQuery
import com.msf.data.model.Profile
import com.msf.data.schemas.Profiles
import com.msf.data.schemas.Profiles.user_id
import com.msf.data.schemas.Users
import com.msf.domain.exceptions.UserNotFoundException
import com.msf.domain.interfaces.ProfileRepository
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class ProfileRepositoryImpl : ProfileRepository {
    override suspend fun createProfile(userId: Int, profileData: String): Profile? = dbQuery {
        val user = Users.select { Users.user_id eq userId }
            .singleOrNull() ?: throw UserNotFoundException("User with ID $userId not found")

        val insertStatement = Profiles.insert {
            it[profile_data] = profileData
            it[user_id] = userId
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToArticle)
    }

    override suspend fun getProfileById(profileId: Int): Profile? = dbQuery {

        Profiles
            .select(Profiles.profile_id eq profileId)
            .map(::resultRowToArticle)
            .singleOrNull()
    }

    override suspend fun getProfileByUserId(userId: Int): Profile? = dbQuery {
        Profiles
            .select(user_id eq userId)
            .map(::resultRowToArticle)
            .singleOrNull()
    }

    override suspend fun getAllProfiles(): List<Profile> = dbQuery {
        Profiles.selectAll().map(::resultRowToArticle)
    }

    override suspend fun editProfile(profileId: Int, newProfileData: String): Boolean = dbQuery {
        val updateRows = Profiles.update({ Profiles.profile_id eq profileId }) {
            it[profile_data] = newProfileData
        }
        updateRows > 0
    }

    override suspend fun deleteProfile(profileId: Int): Boolean = dbQuery {
        val deletedRows = Users.deleteWhere { Users.user_id eq profileId }
        deletedRows > 0
    }

    private fun resultRowToArticle(row: ResultRow) = Profile(
        profile_id = row[Profiles.profile_id],
        user_id = row[Profiles.user_id],
        profile_data = row[Profiles.profile_data],
    )
}