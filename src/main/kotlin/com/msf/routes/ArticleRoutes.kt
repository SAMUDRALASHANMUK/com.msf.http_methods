package com.msf.routes

import com.msf.data.model.Article
import com.msf.data.model.User
import com.msf.data.repositories.ArticlesRepositoryImpl
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureArticleRoutes() {

    val articlesRepository: ArticlesRepositoryImpl by inject()

    routing {
        route("/articles") {

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