package net.unethicalite.backend.service

import net.unethicalite.dto.exception.BackendException
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestTemplate

@Service
class StorageService(
    @Value("\${backend.url}") private val baseUrl: String
) {
    private val restTemplate = RestTemplate()

    fun getFileStream(fileName: String): ByteArray {
        return restTemplate.execute(
            "$baseUrl/storage/$fileName",
            HttpMethod.GET,
            null,
            restTemplate.responseEntityExtractor<ByteArray>(ByteArray::class.java)
        )?.body ?: throw BackendException("Couldn't request file from backend.")
    }

    fun uploadFile(fileName: String, bytes: ByteArray) {
        val headers = HttpHeaders()
        headers.set("Content-Type", MediaType.MULTIPART_FORM_DATA_VALUE)
        val body = LinkedMultiValueMap<String, ByteArray>()
        body.add("file", bytes)
        val entity = HttpEntity(body, headers)
        restTemplate.postForEntity("$baseUrl/storage?fileName=$fileName", entity, Any::class.java)
    }
}