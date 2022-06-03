package net.unethicalite.backend.service

import net.unethicalite.backend.mappers.TransportLinkMapper
import net.unethicalite.backend.repository.TransportLinkRepository
import net.unethicalite.dto.regions.TransportLinkDto
import org.springframework.stereotype.Service

@Service
class TransportService(
    private val transportLinkRepository: TransportLinkRepository,
    private val transportLinkMapper: TransportLinkMapper
) {
    fun saveAll(transports: List<TransportLinkDto>) = transportLinkRepository
        .saveAll(transports
            .filter {
                it.source.split(" ").size == 3
                        && it.destination.split(" ").size == 3
                        && it.action.isNotBlank()
                        && it.objName.isNotBlank()
                        && it.objId > 0
            }.map {
                transportLinkMapper.toModel(it)
            })

    fun getAll() = transportLinkRepository
        .findAllByEnabledTrue()
}
