package ua.wwind.glotov.recipe.converters

import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import ua.wwind.glotov.recipe.domain.Recipe
import ua.wwind.glotov.recipe.dto.RecipeDto

@Component
class RecipeToRecipeDto(
    private val categoryToCategoryDto: CategoryToCategoryDto,
    private val noteToNoteDto: NoteToNoteDto,
    private val ingredientToIngredientDto: IngredientToIngredientDto
) : Converter<Recipe, RecipeDto> {
    override fun convert(recipe: Recipe): RecipeDto? {
        return RecipeDto(
            description = recipe.description,
            id = recipe.id,
            prepTime = recipe.prepTime,
            cookTime = recipe.cookTime,
            servings = recipe.servings,
            source = recipe.source,
            url = recipe.url,
            directions = recipe.directions,
            categories = recipe.categories.mapNotNull { categoryToCategoryDto.convert(it) }.toMutableSet(),
            difficulty = recipe.difficulty,
            note = recipe.note?.let { noteToNoteDto.convert(it) },
            ingredients = recipe.ingredients.mapNotNull { ingredient -> ingredientToIngredientDto.convert(ingredient) }
                .toMutableSet()
        )
    }
}