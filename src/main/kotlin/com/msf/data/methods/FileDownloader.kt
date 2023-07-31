package com.msf.data.methods

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import java.io.File
import io.ktor.client.plugins.*
import io.ktor.client.statement.*

suspend fun HttpClient.downloadFile(url: String, outFile: File) {
    val httpResponse: HttpResponse = get(url) {
        onDownload { bytesSentTotal, contentLength ->
            println("Received $bytesSentTotal bytes from $contentLength")
        }
    }
    val responseBody: ByteArray = httpResponse.body()
    outFile.writeBytes(responseBody)
}


fun getDefaultFileName(url: String): File {
    val fileName = url.substringAfterLast("/")
    return File(fileName)
}