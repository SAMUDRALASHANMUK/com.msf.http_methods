package com.msf

import com.msf.dao.DatabaseFactory
import com.msf.data.MockDatabaseFactory
import com.msf.data.model.User
import com.msf.domain.interfaces.UsersRepository
import com.msf.plugins.*
import io.ktor.server.application.*
import io.ktor.server.netty.*

fun main(args: Array<String>): Unit = EngineMain.main(args)


fun Application.module() {
    //DatabaseFactory.init()
    MockDatabaseFactory.init()
    configureSecurity()
    configureContentNegotiation()
    configureSessions()
    configureRequestValidation()
    configureStatusPages()
    configureKoin()
    configureRouting()
}


