package com.msf.util.appconstants

object GlobalConstants {

    const val REDIS_EXPIRATION_SECONDS = 300
    const val JWT_TOKEN_EXPIRATION_MS = 60000
    const val SESSION_COOKIE_MAX_AGE_SECONDS = 10L
    const val SESSION_ENCRYPT_KEY = "00112233445566778899aabbccddeeff"
    const val SESSION_SIGN_KEY = "6819b57a326945c1968f45236589"
    const val COOKIE_PATH = "/"
    const val MIN_USER_NAME_LENGTH = 2
    const val MAX_USER_NAME_LENGTH = 100
    const val MAX_PASSWORD_LENGTH = 128
    const val MAX_EMAIL_LENGTH = 100
    const val MAX_PROFILE_DATA_LENGTH = 100
    const val MAX_CONTENT_LENGTH = 200
    const val MAX_TITLE_LENGTH = 100
    const val MAX_CATEGORY_LENGTH = 50
    const val MAX_BODY_LENGTH = 100
    const val RANDOM_NUMBER_MIN = 1
    const val RANDOM_NUMBER_MAX = 100
}
