package ua.wwind.glotov.recipe.dto

import ua.wwind.glotov.recipe.domain.Difficulty
import java.io.Serializable

/**
 * A DTO for the {@link ua.wwind.glotov.recipe.domain.Recipe} entity
 */
data class RecipeDto(
    val description: String,
    val id: Long? = null,
    val prepTime: Int? = null,
    val cookTime: Int? = null,
    val servings: Int? = null,
    val source: String? = null,
    val url: String? = null,
    val directions: String? = null,
    val categories: MutableSet<CategoryDto> = mutableSetOf(),
    val difficulty: Difficulty? = null,
    val note: NoteDto? = null,
    val ingredients: MutableSet<IngredientDto> = mutableSetOf()
) : Serializable