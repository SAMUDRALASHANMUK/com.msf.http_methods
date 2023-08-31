package com.msf

import com.msf.plugins.configureSecurity
import com.msf.plugins.configureContentNegotiation
import com.msf.plugins.configureSessions
import com.msf.plugins.configureRequestValidation
import com.msf.plugins.configureStatusPages
import com.msf.plugins.configureKoin
import com.msf.plugins.configureRouting
import io.ktor.server.application.Application
import io.ktor.server.netty.EngineMain

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    //DatabaseFactory.init()
    TestDatabase.init()
    configureSecurity()
    configureContentNegotiation()
    configureSessions()
    configureRequestValidation()
    configureStatusPages()
    configureKoin()
    configureRouting()
    TestDatabase.dropTable()
}
