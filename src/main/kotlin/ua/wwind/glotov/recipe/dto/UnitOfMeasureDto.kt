package ua.wwind.glotov.recipe.dto

import java.io.Serializable

/**
 * A DTO for the {@link ua.wwind.glotov.recipe.domain.UnitOfMeasure} entity
 */
data class UnitOfMeasureDto(var name: String? = null, var id: Long? = null) : Serializable