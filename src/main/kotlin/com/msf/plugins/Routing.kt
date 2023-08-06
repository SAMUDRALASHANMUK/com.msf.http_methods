package com.msf.plugins

import com.msf.routes.*
import configurePostCategoryRoutes
import configurePostRoutes
import io.ktor.server.application.*

fun Application.configureRouting() {
    configureEmpRoutes()
    configureArticleRoutes()
    configurePostRoutes()
    configureProfileRoutes()
    configurePostCategoryRoutes()
    configureCategoryRoutes()
    configureUserSessionRoutes()
    configureUserLoginRoutes()
    configureDownloadRoutes()
    configureUsersRoutes()
}