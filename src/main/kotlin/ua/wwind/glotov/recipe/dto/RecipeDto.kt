package ua.wwind.glotov.recipe.dto

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.URL
import ua.wwind.glotov.recipe.domain.Difficulty
import java.io.Serializable

/**
 * A DTO for the {@link ua.wwind.glotov.recipe.domain.Recipe} entity
 */
data class RecipeDto(
    @field:NotBlank
    @field:Size(min = 3, max = 255)
    var description: String? = null,
    var id: Long? = null,
    @field:Min(1)
    @field:Max(999)
    var prepTime: Int? = null,
    @field:Min(1)
    @field:Max(999)
    var cookTime: Int? = null,
    @field:Min(1)
    @field:Max(100)
    var servings: Int? = null,
    var source: String? = null,
    @field:URL
    @field:NotBlank
    var url: String? = null,
    @field:NotBlank
    var directions: String? = null,
    var categories: MutableSet<CategoryDto> = mutableSetOf(),
    var difficulty: Difficulty? = null,
    var note: NoteDto? = null,
    var ingredients: MutableSet<IngredientDto> = mutableSetOf(),
) : Serializable