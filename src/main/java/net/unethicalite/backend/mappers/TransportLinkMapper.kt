package net.unethicalite.backend.mappers

import net.unethicalite.backend.repository.entity.TransportLink
import net.unethicalite.dto.regions.TransportLinkDto
import org.mapstruct.Mapper

@Mapper(componentModel = "spring")
interface TransportLinkMapper {
    fun toDto(transportLink: TransportLink): TransportLinkDto
    fun toModel(transportLinkDto: TransportLinkDto): TransportLink
}
