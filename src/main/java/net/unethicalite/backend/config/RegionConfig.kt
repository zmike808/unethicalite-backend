package net.unethicalite.backend.config

import net.unethicalite.backend.helpers.collisions.CollisionMap
import net.unethicalite.backend.service.StorageService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.io.ByteArrayInputStream
import java.util.zip.GZIPInputStream

@Configuration
class RegionConfig {
    @Bean
    fun collisionMap(storageService: StorageService): CollisionMap {
        return try {
            CollisionMap(
                GZIPInputStream(
                    ByteArrayInputStream(storageService.getFileStream("regions"))
                ).readAllBytes()
            )
        } catch (e: Exception) {
            throw Exception("Failed to load collision map", e)
        }
    }
}
