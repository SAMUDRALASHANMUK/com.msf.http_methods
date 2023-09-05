package com.msf.routes

import Article
import com.msf.services.ArticleService
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
import java.util.*

fun Application.configureArticleRoutes() {

    val articleService: ArticleService by inject()
    routing {
        route(ARTICLE) {

            get("/") {
                call.respond(articleService.getAllUsers())
            }
            get("/{id}") {
                val id = runCatching { UUID.fromString(call.parameters["id"]) }.getOrNull() ?: return@get call.respondText(
                        "Please provide article id",
                        status = HttpStatusCode.BadRequest
                    )
                val article = articleService.getArticleById(id)
                call.respond(article)
            }
            post("/") {
                val article = call.receive<Article>()
                val response = articleService.createArticle(article)
                call.respond(HttpStatusCode.Created, response)
            }
        }
    }
}
