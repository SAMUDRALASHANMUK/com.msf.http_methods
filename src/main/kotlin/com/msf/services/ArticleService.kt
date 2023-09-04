package com.msf.services

import com.msf.exception.ArticleCreateException

import com.msf.exception.ArticleNotFoundException
import com.msf.model.Article
import com.msf.repository.ArticlesRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ArticleService : KoinComponent {
    private val articlesRepository by inject<ArticlesRepository>()
    suspend fun getAllUsers(): List<Article> {
        return articlesRepository.allArticles()
    }

    suspend fun getArticleById(id: Int): Article {
        val article = articlesRepository.article(id)
        if (article != null) {
            return article
        } else {
            throw ArticleNotFoundException()
        }
    }

    suspend fun createArticle(article: Article): Article {
        val article = articlesRepository.addNewArticle(article.title, article.body)
        return article ?: throw ArticleCreateException()

    }

}