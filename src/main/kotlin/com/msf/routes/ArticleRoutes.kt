package com.msf.routes

import com.msf.data.model.Article
import com.msf.data.repositories.ArticlesRepositoryImpl
import com.msf.util.appconstants.ApiEndPoints.ARTICLE
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.application.Application
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import io.ktor.server.routing.route
import io.ktor.server.routing.post
import org.koin.ktor.ext.inject

fun Application.configureArticleRoutes() {

    val articlesRepository: ArticlesRepositoryImpl by inject()

    routing {
        route(ARTICLE) {

            get("/") {
                call.respond(articlesRepository.allArticles())
            }
            get("/{id}") {
                val id = Integer.parseInt(call.parameters["id"])
                call.respondText(articlesRepository.article(id).toString())
            }
            post("/") {
                val article = call.receive<Article>()
                val createdArticle = articlesRepository.addNewArticle(article.title, article.body)
                if (createdArticle != null) {
                    call.respond(HttpStatusCode.Created, createdArticle)
                } else {
                    call.respond(HttpStatusCode.InternalServerError)
                }
            }
        }
    }
}
