package ua.wwind.glotov.recipe.dto

import java.io.Serializable

/**
 * A DTO for the {@link ua.wwind.glotov.recipe.domain.Category} entity
 */
data class CategoryDto(var name: String? = null, var id: Long? = null) : Serializable