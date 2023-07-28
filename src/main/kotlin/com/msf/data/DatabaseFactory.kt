package com.msf.dao

import com.msf.data.schemas.*
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
            SchemaUtils.createMissingTablesAndColumns(
                Users,
                Posts,
                Profiles,
                Categories,
                PostCategories,
                UserLoginTable
            )
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}