package com.msf.data.methods

import com.msf.data.model.Profile
import com.msf.data.schemas.Profiles
import org.jetbrains.exposed.sql.max
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

fun getLatestUserIdFromDatabase(): Int {

    return transaction {

        Profiles
            .slice(Profiles.profile_id.max())
            .selectAll()
            .map { it[Profiles.user_id.max()] ?: 0 }
            .single()
    }
}