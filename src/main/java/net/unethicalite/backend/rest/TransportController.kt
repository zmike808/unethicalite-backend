package net.unethicalite.backend.rest

import net.unethicalite.backend.mappers.TransportLinkMapper
import net.unethicalite.backend.service.TransportService
import net.unethicalite.dto.regions.TransportLinkDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/transports")
class TransportController(
    private val transportService: TransportService,
    private val transportLinkMapper: TransportLinkMapper
) {
    @GetMapping
    fun getTransports() = transportService.getAll().map { transportLinkMapper.toDto(it) }

    @PostMapping
    fun saveTransports(@RequestBody transports: List<TransportLinkDto>) = ResponseEntity.noContent().let {
        transportService.saveAll(transports)
    }
}
