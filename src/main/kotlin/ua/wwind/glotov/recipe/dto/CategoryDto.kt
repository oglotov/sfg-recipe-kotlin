package ua.wwind.glotov.recipe.dto

import java.io.Serializable

/**
 * A DTO for the {@link ua.wwind.glotov.recipe.domain.Category} entity
 */
data class CategoryDto(val name: String, val id: Long? = null) : Serializable