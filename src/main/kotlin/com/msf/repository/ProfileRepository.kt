package com.msf.repository

import com.msf.config.DatabaseFactory.dbQuery
import com.msf.dao.ProfileDAO
import com.msf.database.table.Profiles
import com.msf.database.table.Profiles.user_id
import com.msf.database.table.Users
import com.msf.exception.UserNotFoundException
import com.msf.model.Profile
import com.msf.util.helperfunctions.resultRowToProfile
import kotlinx.serialization.Contextual
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.update
import org.jetbrains.exposed.sql.selectAll

import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.util.UUID

class ProfileRepository : ProfileDAO {
    override suspend fun createProfile(userId: EntityID<@Contextual UUID>, profileData: String): Profile? = dbQuery {
        Users.select { Users.id eq userId }
            .singleOrNull() ?: throw UserNotFoundException()

        val insertStatement = Profiles.insert {
            it[profile_data] = profileData
            it[user_id] = userId
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToProfile)
    }

    override suspend fun getProfileById(profileId: UUID): Profile? = dbQuery {

        Profiles
            .select(Profiles.id eq profileId)
            .map(::resultRowToProfile)
            .singleOrNull()
    }

    override suspend fun getProfileByUserId(userId: EntityID<@Contextual UUID>): Profile? = dbQuery {
        Profiles
            .select(user_id eq userId)
            .map(::resultRowToProfile)
            .singleOrNull()
    }

    override suspend fun getAllProfiles(): List<Profile> = dbQuery {
        Profiles.selectAll().map(::resultRowToProfile)
    }

    override suspend fun editProfile(profileId: UUID, newProfileData: String): Boolean = dbQuery {
        val updateRows = Profiles.update({ Profiles.id eq profileId }) {
            it[profile_data] = newProfileData
        }
        updateRows > 0
    }

    override suspend fun deleteProfile(profileId: UUID): Boolean = dbQuery {
        val deletedRows = Users.deleteWhere { user_id eq profileId }
        deletedRows > 0
    }
}
