package ua.wwind.glotov.recipe.dto

import ua.wwind.glotov.recipe.domain.Difficulty
import java.io.Serializable

/**
 * A DTO for the {@link ua.wwind.glotov.recipe.domain.Recipe} entity
 */
data class RecipeDto(
    var description: String? = null,
    var id: Long? = null,
    var prepTime: Int? = null,
    var cookTime: Int? = null,
    var servings: Int? = null,
    var source: String? = null,
    var url: String? = null,
    var directions: String? = null,
    var categories: MutableSet<CategoryDto> = mutableSetOf(),
    var difficulty: Difficulty? = null,
    var note: NoteDto? = null,
    var ingredients: MutableSet<IngredientDto> = mutableSetOf()
) : Serializable