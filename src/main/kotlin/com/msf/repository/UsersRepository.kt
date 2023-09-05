package com.msf.repository

import com.msf.config.DatabaseFactory.dbQuery
import com.msf.dao.UserDAO
import com.msf.database.table.Users
import com.msf.model.User
import com.msf.util.helperfunctions.resultRowToUser
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import java.util.UUID
@Serializable
data class userId1(val id: String)

fun mapUserId(row: ResultRow): userId1 {
    return userId1(row[Users.id].toString())
}

open class UsersRepository : UserDAO {
    override suspend fun createUser(username: String, email: String): userId1? = dbQuery {

        val insertStatement = Users.insert {
            it[user_name] = username
            it[Users.email] = email
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::mapUserId)!!
    }


    override suspend fun getUserById(userId: UUID): User? = dbQuery {
        Users
            .select(Users.id eq userId)
            .map(::resultRowToUser)
            .singleOrNull()
    }

    override suspend fun getAllUsers(): List<User> = dbQuery {

        Users.selectAll().map(::resultRowToUser)
    }

    override suspend fun editUser(userId: UUID, newUsername: String, newEmail: String): Boolean = dbQuery {

        val updateRows = Users.update({ Users.id eq userId }) {
            it[user_name] = newUsername
            it[email] = email
        }
        updateRows > 0
    }

    override suspend fun deleteUser(userId: UUID): Boolean = dbQuery {
        val deletedRows = Users.deleteWhere { Users.id eq userId }
        deletedRows > 0
    }
}
