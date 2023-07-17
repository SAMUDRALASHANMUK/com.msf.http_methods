package com.msf.routes

import com.msf.data.repositories.ArticlesRepositoryImpl
import com.msf.domain.interfaces.ArticlesRepository
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureArticleRoutes() {
    val daoArticlesRepository: ArticlesRepository = ArticlesRepositoryImpl()
    routing {
        route("/articles") {

            get("/") {
                call.respond(daoArticlesRepository.allArticles())
            }
            get("{/id}") {
                val id = Integer.parseInt(call.parameters["id"])
                call.respondText(daoArticlesRepository.article(id).toString())
            }
        }
    }
}