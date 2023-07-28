package com.msf.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import java.io.File

=


fun Application.configureDownloadRoutes() {
    routing {
        get("/fileDownload") {
            val file = File("files/americano.jpg")
            call.response.header(
                HttpHeaders.ContentDisposition,
                ContentDisposition.Attachment.withParameter(
                    ContentDisposition.,
                    "downloadableimage.jpg"
                ).toString()
            )
        }
    }
}