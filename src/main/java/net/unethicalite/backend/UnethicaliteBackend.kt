package net.unethicalite.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UnethicaliteBackend

fun main(args: Array<String>) {
    runApplication<UnethicaliteBackend>(*args)
}
