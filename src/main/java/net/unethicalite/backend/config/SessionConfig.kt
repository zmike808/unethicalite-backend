package net.unethicalite.backend.config

import net.unethicalite.backend.config.properties.SessionProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableScheduling

@Configuration
@EnableScheduling
@EnableConfigurationProperties(SessionProperties::class)
class SessionConfig