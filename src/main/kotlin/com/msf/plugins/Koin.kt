package com.msf.plugins

import com.msf.data.di.myModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin

fun Application.configureKoin(){
    install(Koin){
        modules(myModule)
    }
}