package com.msf.plugins

import com.msf.di.myModule
import io.ktor.server.application.Application
import io.ktor.server.application.install
import org.koin.ktor.plugin.Koin

fun Application.configureKoin() {


    install(Koin) {
        modules(myModule)
    }
}
