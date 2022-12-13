package ua.wwind.glotov.recipe.converters

import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import ua.wwind.glotov.recipe.domain.Recipe
import ua.wwind.glotov.recipe.dto.RecipeDto

@Component
class RecipeDtoToRecipe(
    private val categoryDtoToCategory: CategoryDtoToCategory,
    private val noteDtoToNote: NoteDtoToNote,
    private val ingredientDtoToIngredient: IngredientDtoToIngredient
) : Converter<RecipeDto, Recipe> {
    override fun convert(dto: RecipeDto): Recipe? {
        return Recipe(description = dto.description)
            .apply {
                id = dto.id
                prepTime = dto.prepTime
                cookTime = dto.cookTime
                servings = dto.servings
                source = dto.source
                url = dto.url
                directions = dto.directions
                categories = dto.categories.mapNotNull { categoryDtoToCategory.convert(it) }.toMutableSet()
                difficulty = dto.difficulty
                note = dto.note?.let { noteDtoToNote.convert(it) }
            }.also { recipe ->
                dto.ingredients.forEach { ingredientDto ->
                    ingredientDtoToIngredient.convert(ingredientDto)?.let { recipe.addIngredient(it) }
                }
            }
    }
}