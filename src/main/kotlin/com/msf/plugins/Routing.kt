package com.msf.plugins

import com.msf.routes.configureUsersRoutes
import com.msf.routes.configureEmpRoutes
import com.msf.routes.configureArticleRoutes
import com.msf.routes.configurePostRoutes
import com.msf.routes.configureProfileRoutes
import com.msf.routes.configurePostCategoryRoutes
import com.msf.routes.configureCategoryRoutes
import com.msf.routes.configureUserSessionRoutes
import com.msf.routes.configureUserLoginRoutes
import com.msf.routes.configureDownloadRoutes
import com.msf.routes.configurePostClientRoutes
import io.ktor.server.application.Application

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
    configurePostClientRoutes()
}
