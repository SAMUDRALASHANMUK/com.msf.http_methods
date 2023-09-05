package com.msf.dao

import Article
import java.util.UUID


interface ArticleDAO {
    suspend fun allArticles(): List<Article>
    suspend fun article(id: UUID): Article?
    suspend fun addNewArticle(title: String, body: String): Article?
    suspend fun editArticle(id: UUID, title: String, body: String): Boolean
    suspend fun deleteArticle(id: UUID): Boolean
}
