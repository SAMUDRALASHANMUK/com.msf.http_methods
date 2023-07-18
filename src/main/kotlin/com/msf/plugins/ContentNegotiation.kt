package com.msf.plugins

import com.google.gson.Gson
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.json.Json


fun Application.configureContentNegotiation() {
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
            clearIgnoredTypes()

        }


        }

    }
