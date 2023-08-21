package com.msf.routes

import com.msf.data.methods.downloadFile
import com.msf.data.methods.getDefaultFileName
import io.ktor.client.HttpClient
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.routing
import io.ktor.server.routing.get
import java.io.File

fun Application.configureDownloadRoutes() {
    routing {
        val client = HttpClient()

        get("/download") {
            val fileUrl = call.parameters["url"]
            val customFileName = call.parameters["filename"]
            if (fileUrl.isNullOrEmpty()){
                call.respond(HttpStatusCode.BadRequest, "Error: Please provide a valid file URL.")
                return@get
            }
            val outputFile = customFileName?.let { File(it) } ?: getDefaultFileName(fileUrl)
            try {
                client.downloadFile(fileUrl, outputFile)
                call.respond(HttpStatusCode.OK, "File downloaded and saved to ${outputFile.path}")
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "Error downloading the file: ${e.message}")
            }
        }
    }
}
