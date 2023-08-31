package com.msf.repository

import com.msf.dao.UserLoginDAO
import com.msf.config.DatabaseFactory.dbQuery
import com.msf.data.schemas.UserLoginTable
import com.msf.model.UserLogin
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.and
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select


class UserLoginImpl : UserLoginDAO {

    private fun getResultToUserLogin(row: ResultRow): UserLogin =
        UserLogin(
            row[UserLoginTable.userName], row[UserLoginTable.password]
        )

    override suspend fun createUser(userName: String, password: String): UserLogin? = dbQuery {
        val insertStatement = UserLoginTable.insert {
            it[UserLoginTable.userName] = userName
            it[UserLoginTable.password] = password
        }
        insertStatement.resultedValues?.map(::getResultToUserLogin)?.singleOrNull()
    }

    override suspend fun getUser(userName: String, password: String): UserLogin? = dbQuery {
        UserLoginTable
            .select(UserLoginTable.userName eq userName and (UserLoginTable.password eq password))
            .map(::getResultToUserLogin)
            .singleOrNull()
    }
}
