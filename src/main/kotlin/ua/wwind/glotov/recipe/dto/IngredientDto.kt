package ua.wwind.glotov.recipe.dto

import java.io.Serializable
import java.math.BigDecimal

/**
 * A DTO for the {@link ua.wwind.glotov.recipe.domain.Ingredient} entity
 */
data class IngredientDto(
    val description: String = "",
    val amount: BigDecimal? = null,
    val unitOfMeasure: UnitOfMeasureDto? = null,
    val id: Long? = null
) : Serializable