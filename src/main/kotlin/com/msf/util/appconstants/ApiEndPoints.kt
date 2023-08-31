package com.msf.util.appconstants

object ApiEndPoints {
    const val USER = "/users"
    const val DELETE_USER = "/{id}"
    const val UPDATE_USER = "/{id}"
    const val GET_USER = "/{id}"
    const val ARTICLE = "/articles"
    const val CATEGORY = "/categories"
    const val GET_CATEGORY = "/{id}"
    const val UPDATE_CATEGORY = "/{id}"
    const val DELETE_CATEGORY = "/{id}"
    const val EMPLOYEE = "/employees"
    const val POST = "/posts"
    const val PROFILE = "/profiles"
    const val CATEGORY_POSTS_PATH = "/categories/{category_id}/posts"
    const val POST_CATEGORIES_PATH = "/posts/{post_id}/categories"
    const val CLIENT_API = "/api/data"
    const val DELETE_EMPLOYEE = "/{id?}"
    const val EDIT_EMPLOYEE_NAME = "/{id?}"
    const val GET_EMPLOYEE = "/{id?}"
}
