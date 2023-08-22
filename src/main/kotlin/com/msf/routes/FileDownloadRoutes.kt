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
import java.io.FileNotFoundException
import java.io.IOException

fun Application.configureDownloadRoutes() {
    routing {
        val client = HttpClient()
        get("/download") {
            val fileUrl = call.parameters["url"]
            val customFileName = call.parameters["filename"]
            if (fileUrl.isNullOrEmpty()) {
                call.respond(HttpStatusCode.BadRequest, "Error: Please provide a valid file URL.")
                return@get
            }
            val outputFile = customFileName?.let { File(it) } ?: getDefaultFileName(fileUrl)
            try {
                client.downloadFile(fileUrl, outputFile)
                call.respond(HttpStatusCode.OK, "File downloaded and saved to ${outputFile.path}")
            } catch (e: IOException) {
                call.respond(HttpStatusCode.InternalServerError, "An I/O error occurred : ${e.message}")
            } catch (e: FileNotFoundException) {
                call.respond(HttpStatusCode.InternalServerError, "The specified file does not exist: ${e.message}")
            } catch (e: SecurityException) {
                call.respond(HttpStatusCode.InternalServerError, "Security exception: ${e.message}")
            }
        }
    }
}

