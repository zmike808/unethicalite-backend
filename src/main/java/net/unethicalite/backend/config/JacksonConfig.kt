package net.unethicalite.backend.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JacksonConfig {
    @Bean
    fun objectMapper() = ObjectMapper()
        .setSerializationInclusion(JsonInclude.Include.NON_NULL)
}