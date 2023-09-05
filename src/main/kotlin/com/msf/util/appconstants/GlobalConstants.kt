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
    const val ARTICLE_TABLE_NAME = "Articles"
    const val ARTICLE_TITLE = "title"
    const val ARTICLE_BODY = "body"
    const val CATEGORY_NAME = "category_name"
    const val CATEGORY_ID = "id"
    const val CATEGORY_TABLE_NAME = "categories_table"
    const val POST_CATEGORIES_TABLE_NAME = "post_categories"
    const val POSTS_TABLE_NAME = "posts_table"
    const val POST_CONTENT = "content"
    const val POST_TITLE = "title"
    const val POST_ID = "id"
    const val PROFILE_TABLE_NAME = "profiles_table"
    const val PROFILE_DATA = "profile_data"
    const val USER_LOGIN_TABLE_NAME = "user login_table"
    const val USER_ID = "id"
    const val USER_TABLE_NAME = "users_table"
    const val USER_NAME = "user_name"
    const val USER_EMAIL = "email"
    const val USER_LOGIN_NAME = "userName"
    const val USER_LOGIN_PASSWORD = "password"
}
