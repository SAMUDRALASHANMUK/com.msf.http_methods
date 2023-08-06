
package com.msf.data.methods

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import java.io.File
import io.ktor.client.plugins.*
import io.ktor.client.statement.*
import io.ktor.http.*

// Define a suspend function to download a file from a given URL and save it to the local machine
suspend fun HttpClient.downloadFile(url: String, outFile: File) {
    // Make an HTTP GET request using the Ktor HTTP client
    val httpResponse: HttpResponse = get(url) {
        // Define a callback function to track download progress
        onDownload { bytesSentTotal, contentLength ->
            println("Received $bytesSentTotal bytes from $contentLength")
        }
        headers {
            append(HttpHeaders.UserAgent, "Mozilla/5.0") // Set a custom user-agent
        }
    }

    // Extract the response body as a byte array
    val responseBody: ByteArray = httpResponse.body<ByteArray>()

    // Write the received bytes to the specified outFile
    outFile.writeBytes(responseBody)
}
// Function to get the default filename from a given URL
fun getDefaultFileName(url: String): File {
    // Extract the filename from the URL by finding the substring after the last "/"
    val fileName = url.substringAfterLast("/")

    // Create a File object using the extracted filename
    return File(fileName)
}