package ua.wwind.glotov.recipe.dto

import java.io.Serializable

/**
 * A DTO for the {@link ua.wwind.glotov.recipe.domain.UnitOfMeasure} entity
 */
data class UnitOfMeasureDto(val name: String? = null, val id: Long? = null) : Serializable