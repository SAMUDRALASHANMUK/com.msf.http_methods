package com.msf.model

import io.ktor.http.*

data class CreateUserResponse(val status: HttpStatusCode, val user: User?)

