package com.msf.services

import com.msf.config.status.ArticleCreateException
import com.msf.config.status.ArticleNotFoundException
import com.msf.model.Article
import com.msf.model.CreateArticleResponse
import com.msf.repository.ArticlesRepositoryImpl
import io.ktor.http.*

class ArticleService {
    private val articlesRepositoryImpl = ArticlesRepositoryImpl()
    suspend fun getAllUsers(): List<Article> {
        return articlesRepositoryImpl.allArticles()
    }

    suspend fun getArticleById(id: Int): Article {
        val article = articlesRepositoryImpl.article(id)
        if (article != null) {
            return article
        } else {
            throw ArticleNotFoundException()
        }
    }

    suspend fun createArticle(article: Article): Article {
        val article = articlesRepositoryImpl.addNewArticle(article.title, article.body)
        return article ?: throw ArticleCreateException()

    }

}