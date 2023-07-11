package com.msf

import com.msf.plugins.configureContentNegotiation
import com.msf.plugins.configureRequestValidation
import com.msf.plugins.configureStatusPages
import com.msf.routes.configureEmpRoutes
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
            .start(wait = true)
}
fun Application.module() {

    configureContentNegotiation()
    configureRequestValidation()
    configureStatusPages()
    configureEmpRoutes()
}


