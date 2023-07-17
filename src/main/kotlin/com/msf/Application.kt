package com.msf

import com.msf.dao.DatabaseFactory
import com.msf.plugins.configureContentNegotiation
import com.msf.plugins.configureRequestValidation
import com.msf.plugins.configureStatusPages
import com.msf.routes.configureArticleRoutes
import com.msf.routes.configureEmpRoutes
import com.msf.routes.configureProfileRoutes
import com.msf.routes.configureUsersRoutes
import configurePostRoutes
import io.ktor.server.application.*
import io.ktor.server.netty.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    DatabaseFactory.init()
    configureContentNegotiation()
    configureRequestValidation()
    configureStatusPages()
    configureEmpRoutes()
    configureArticleRoutes()
    configureUsersRoutes()
    configurePostRoutes()
    configureProfileRoutes()
}
