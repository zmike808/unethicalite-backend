package net.unethicalite.backend.repository

import net.unethicalite.backend.config.properties.SessionProperties
import net.unethicalite.backend.repository.entity.Session
import org.springframework.stereotype.Repository
import java.time.Instant
import java.util.UUID

@Repository
class SessionRepository(
    private val sessionProperties: SessionProperties
) {
    private val sessions = mutableMapOf<String, Session>()

    fun findById(uuid: String) = sessions[uuid]

    fun newSession(mode: String) = UUID.randomUUID().run {
        sessions[toString()] = Session(this, mode)
        toString()
    }

    fun delete(uuid: String) = sessions.remove(uuid)

    fun count() = sessions.size

    fun removeExpired() = sessions.entries.removeIf { (_, v) ->
        v.lastUpdate.plusMillis(sessionProperties.maxAge).isBefore(Instant.now())
    }
}