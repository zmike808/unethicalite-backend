package net.unethicalite.backend.rest

import net.unethicalite.backend.repository.SessionRepository
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.Instant
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/sessions")
class SessionController(
    private val sessionRepository: SessionRepository
) {
    @GetMapping
    fun count() = sessionRepository.count()

    @PostMapping
    fun newSession(@RequestParam mode: String, httpServletRequest: HttpServletRequest) =
        sessionRepository.newSession(httpServletRequest.remoteAddr, mode)

    @PutMapping
    fun ping(httpServletRequest: HttpServletRequest) {
        sessionRepository.findById(httpServletRequest.remoteAddr)?.lastUpdate = Instant.now()
    }

    @DeleteMapping
    fun delete(httpServletRequest: HttpServletRequest) {
        sessionRepository.delete(httpServletRequest.remoteAddr)
    }
}