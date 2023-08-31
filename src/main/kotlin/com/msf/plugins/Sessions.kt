package com.msf.plugins

import com.msf.model.UserSession
import com.msf.util.appconstants.GlobalConstants.COOKIE_PATH
import com.msf.util.appconstants.GlobalConstants.SESSION_COOKIE_MAX_AGE_SECONDS
import com.msf.util.appconstants.GlobalConstants.SESSION_ENCRYPT_KEY
import com.msf.util.appconstants.GlobalConstants.SESSION_SIGN_KEY
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.sessions.Sessions
import io.ktor.server.sessions.cookie
import io.ktor.server.sessions.SessionTransportTransformerEncrypt

import io.ktor.util.hex

fun Application.configureSessions() {
    install(Sessions) {
        val secretEncryptKey = hex(SESSION_ENCRYPT_KEY)
        val secretSignKey = hex(SESSION_SIGN_KEY)
        cookie<UserSession>("user_session") {
            cookie.path = COOKIE_PATH
            cookie.maxAgeInSeconds = SESSION_COOKIE_MAX_AGE_SECONDS
            transform(SessionTransportTransformerEncrypt(secretEncryptKey, secretSignKey))
        }
    }
}

