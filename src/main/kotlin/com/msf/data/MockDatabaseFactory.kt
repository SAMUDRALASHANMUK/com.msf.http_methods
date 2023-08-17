package com.msf.data

import com.msf.data.schemas.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object MockDatabaseFactory {
    fun init() {
        Database.connect("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;", driver = "org.h2.Driver")
    }

    fun initializeMockData() {
        transaction {
            SchemaUtils.createMissingTablesAndColumns(
                Articles,
                Users,
                Posts,
                Profiles,
                Categories,
                PostCategories,
                UserLoginTable
            )
        }
    }

    fun dropMockData() {
        transaction {
            SchemaUtils.createMissingTablesAndColumns(
                Articles,
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
        newSuspendedTransaction(Dispatchers.Default) { block() }
}
