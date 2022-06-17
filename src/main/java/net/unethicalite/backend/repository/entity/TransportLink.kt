package net.unethicalite.backend.repository.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class TransportLink(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long?,
    val source: String,
    val destination: String,
    val action: String,
    val objName: String,
    val objId: Int,
    val description: String?,
    val requirementsJson: String = "{}",
    val enabled: Boolean = false,
)
