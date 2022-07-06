package net.unethicalite.backend.repository.entity

import java.time.Instant
import java.util.UUID

data class Session(
    val uuid: UUID,
    val mode: String,
    var lastUpdate: Instant = Instant.now()
)