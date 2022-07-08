package net.unethicalite.backend.rest

import net.unethicalite.backend.repository.SessionRepository
import org.springframework.web.bind.annotation.*
import java.time.Instant

@RestController
@RequestMapping("/sessions")
class SessionController(
    private val sessionRepository: SessionRepository
) {
    @GetMapping
    fun count() = sessionRepository.count()

    @PostMapping
    fun newSession(@RequestParam mode: String) =
        sessionRepository.newSession(mode)

    @PutMapping
    fun ping(@RequestParam session: String) {
        sessionRepository.findById(session)?.lastUpdate = Instant.now()
    }

    @DeleteMapping
    fun delete(@RequestParam session: String) {
        sessionRepository.delete(session)
    }
}