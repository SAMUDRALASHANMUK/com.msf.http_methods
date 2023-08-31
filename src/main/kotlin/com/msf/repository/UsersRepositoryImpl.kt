package com.msf.repository

//import com.msf.config.DatabaseFactory.dbQuery
import com.msf.dao.UserDAO
import com.msf.data.schemas.Users
import com.msf.model.User
import com.msf.util.helperfunctions.resultRowToUser
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.update
import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

open class UsersRepositoryImpl : UserDAO {
    override suspend fun createUser(username: String, email: String): User? = dbQuery {

        val insertStatement = Users.insert {
            it[user_name] = username
            it[Users.email] = email
        }
        insertStatement.resultedValues?.singleOrNull()?.let(::resultRowToUser)
    }


    override suspend fun getUserById(userId: Int): User? = dbQuery {
        Users
            .select(Users.user_id eq userId)
            .map(::resultRowToUser)
            .singleOrNull()
    }

    override suspend fun getAllUsers(): List<User> = dbQuery {

        Users.selectAll().map(::resultRowToUser)
    }

    override suspend fun editUser(userId: Int, newUsername: String, newEmail: String): Boolean = dbQuery {

        val updateRows = Users.update({ Users.user_id eq userId }) {
            it[user_name] = newUsername
            it[email] = email
        }
        updateRows > 0
    }

    override suspend fun deleteUser(userId: Int): Boolean = dbQuery {
        val deletedRows = Users.deleteWhere { user_id eq userId }
        deletedRows > 0
    }
}
