package com.msf.exception

class ArticleNotFoundException : RuntimeException("Article not found in the database")

class ArticleCreateException : RuntimeException("Failed to create a new Article")
