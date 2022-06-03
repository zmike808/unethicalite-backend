package net.unethicalite.backend.repository

import net.unethicalite.backend.repository.entity.TransportLink
import org.springframework.data.jpa.repository.JpaRepository

interface TransportLinkRepository : JpaRepository<TransportLink, Long> {
    fun findAllByEnabledTrue(): List<TransportLink>
}
