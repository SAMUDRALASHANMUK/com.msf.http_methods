package com.msf

import com.msf.dao.DatabaseFactory
import com.msf.plugins.*
import io.ktor.server.application.*
import io.ktor.server.netty.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    DatabaseFactory.init()
    configureSecurity()
    configureContentNegotiation()
    configureSessions()
    configureRequestValidation()
    configureRouting()
    configureStatusPages()
}
