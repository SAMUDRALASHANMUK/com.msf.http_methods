package com.msf.dao

import com.msf.data.schemas.*
import com.typesafe.config.ConfigFactory
import io.ktor.server.config.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction


object DatabaseFactory {
    fun init() {
        val config = HoconApplicationConfig(ConfigFactory.load())
        val driver = config.property("ktor.database.driver").getString()
        val url = config.property("ktor.database.url").getString()
        val user = config.property("ktor.database.user").getString()
        val password = config.property("ktor.database.password").getString()
        Database.connect(url = url, driver = driver, user = user, password = password)


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


