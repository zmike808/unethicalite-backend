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

    fun findById(host: String) = sessions[host]

    fun newSession(host: String, mode: String) = UUID.randomUUID().run {
        println("New user connected in $mode mode")
        sessions[host] = Session(this, mode)
        toString()
    }

    fun delete(host: String) = sessions.remove(host)

    fun count() = sessions.size

    fun removeExpired() = sessions.entries.removeIf { (_, v) ->
        v.lastUpdate.plusMillis(sessionProperties.maxAge).isBefore(Instant.now())
    }
}