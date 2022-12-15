package ua.wwind.glotov.recipe.converters

import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import ua.wwind.glotov.recipe.domain.Ingredient
import ua.wwind.glotov.recipe.domain.Recipe
import ua.wwind.glotov.recipe.dto.IngredientDto

@Component
class IngredientDtoToIngredient(private val uomConverter: UnitOfMeasureDtoToUnitOfMeasure) :
    Converter<IngredientDto, Ingredient> {
    override fun convert(source: IngredientDto): Ingredient {
        val recipeRef = Recipe("").apply { id = source.recipeId }
        return Ingredient(
            description = source.description,
            amount = source.amount,
            unitOfMeasure = source.unitOfMeasure?.let { uomConverter.convert(it) },
        ).apply {
            id = source.id
            recipe = recipeRef
        }
    }
}