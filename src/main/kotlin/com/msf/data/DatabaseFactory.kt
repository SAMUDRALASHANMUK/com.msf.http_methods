package com.msf.dao

import com.msf.data.schemas.Articles
import com.msf.data.schemas.Posts
import com.msf.data.schemas.Profiles
import com.msf.data.schemas.Users
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction


object DatabaseFactory {
    fun init() {
        val url = "jdbc:postgresql://localhost:5432/relationships"
        val driver = "org.postgresql.Driver"
        val user = "postgres"
        val password = "Shanmuk123#"
        Database.connect(url, driver, user, password)

        transaction {
            SchemaUtils.create(Articles)
            SchemaUtils.createMissingTablesAndColumns(Users)
            SchemaUtils.createMissingTablesAndColumns(Posts)
            SchemaUtils.createMissingTablesAndColumns(Profiles)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}