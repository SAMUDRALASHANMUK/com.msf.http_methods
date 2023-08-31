package com.msf.model

import io.ktor.http.*

data class CreateArticleResponse(val status:HttpStatusCode, val article: Article?)
