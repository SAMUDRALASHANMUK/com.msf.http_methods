package com.msf

import com.msf.plugins.configureContentNegotiation
import com.msf.plugins.configureRequestValidation
import com.msf.plugins.configureStatusPages
import com.msf.routes.configureEmpRoutes
import io.ktor.server.application.*
import io.ktor.server.netty.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    configureContentNegotiation()
    configureRequestValidation()
    configureStatusPages()
    configureEmpRoutes()
}
