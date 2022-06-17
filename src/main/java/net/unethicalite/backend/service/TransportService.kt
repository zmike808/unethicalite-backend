package net.unethicalite.backend.service

import com.fasterxml.jackson.databind.ObjectMapper
import net.unethicalite.backend.repository.TransportLinkRepository
import net.unethicalite.backend.repository.entity.TransportLink
import net.unethicalite.dto.regions.TransportLinkDto
import org.springframework.stereotype.Service

@Service
class TransportService(
    private val transportLinkRepository: TransportLinkRepository,
    private val objectMapper: ObjectMapper
) {
    fun saveAll(transports: List<TransportLinkDto>) = transportLinkRepository
        .saveAll(transports.map {
                TransportLink(
                    null,
                    it.source.toString(),
                    it.destination.toString(),
                    it.action,
                    it.objectName,
                    it.objectId,
                    it.description,
                    objectMapper.writeValueAsString(it.requirement)
                )
            })

    fun getAll() = transportLinkRepository
        .findAllByEnabledTrue()
}
