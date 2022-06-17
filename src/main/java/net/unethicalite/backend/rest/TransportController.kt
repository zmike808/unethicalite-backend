package net.unethicalite.backend.rest

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import net.unethicalite.backend.service.TransportService
import net.unethicalite.dto.regions.TransportLinkDto
import net.unethicalite.dto.regions.WorldPointDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/transports")
class TransportController(
    private val transportService: TransportService,
    private val objectMapper: ObjectMapper
) {
    @GetMapping
    fun getTransports() = transportService.getAll().map {
        TransportLinkDto(
            it.source.split(" ").map { str -> str.toInt() }.run {
                WorldPointDto(this[0], this[1], this[2])
            },
            it.destination.split(" ").map { str -> str.toInt() }.run {
                WorldPointDto(this[0], this[1], this[2])
            },
            it.action,
            it.objId,
            it.objName,
            it.description,
            objectMapper.readValue(it.requirementsJson)
        )
    }

    @PostMapping
    fun saveTransports(@RequestBody transports: List<TransportLinkDto>) = ResponseEntity.noContent().let {
        transportService.saveAll(transports)
    }
}
