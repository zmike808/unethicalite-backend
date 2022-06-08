package net.unethicalite.backend.rest

import net.unethicalite.backend.service.RegionService
import net.unethicalite.dto.regions.TileFlagDto
import org.springframework.core.io.InputStreamResource
import org.springframework.http.MediaType
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import java.io.ByteArrayInputStream
import javax.validation.Valid

@RestController
@RequestMapping("/regions")
@Validated
class RegionController(
    private val regionService: RegionService,
) {
    @Deprecated(message = "Will be removed as db version check is no longer needed")
    @PostMapping("/{version}")
    fun saveAll(@PathVariable version: Int, @Valid @RequestBody tiles: List<TileFlagDto>) {
        regionService.save(tiles)
    }

    @PostMapping
    fun saveAll(@Valid @RequestBody tiles: List<TileFlagDto>) {
        regionService.save(tiles)
    }

    @GetMapping("/instance/{region}")
    fun instance(@PathVariable region: Int) = regionService.deleteInstancedRegion(region)

    @GetMapping(produces = [MediaType.APPLICATION_OCTET_STREAM_VALUE])
    fun getAll() = InputStreamResource(ByteArrayInputStream(regionService.getFile()))

    @GetMapping("/unmapped")
    fun getUnmapped() = regionService.getUnmapped()

    @GetMapping("/{region}/{z}")
    fun getRegion(@PathVariable region: Int, @PathVariable z: Int) = regionService.getMappedTiles(region, z)

    @GetMapping("/{region}")
    fun getRegion(@PathVariable region: Int) = regionService.getMappedTiles(region)
}
