package net.unethicalite.backend.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "sessions")
@ConstructorBinding
data class SessionProperties(
    val maxAge: Long
)