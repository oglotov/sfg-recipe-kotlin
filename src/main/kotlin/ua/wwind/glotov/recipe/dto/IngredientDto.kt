package ua.wwind.glotov.recipe.dto

import java.io.Serializable
import java.math.BigDecimal

/**
 * A DTO for the {@link ua.wwind.glotov.recipe.domain.Ingredient} entity
 */
data class IngredientDto(
    var description: String = "",
    var amount: BigDecimal? = null,
    var unitOfMeasure: UnitOfMeasureDto? = null,
    var id: Long? = null
) : Serializable