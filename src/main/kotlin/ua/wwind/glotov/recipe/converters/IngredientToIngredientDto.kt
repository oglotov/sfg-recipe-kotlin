package ua.wwind.glotov.recipe.converters

import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import ua.wwind.glotov.recipe.domain.Ingredient
import ua.wwind.glotov.recipe.dto.IngredientDto

@Component
class IngredientToIngredientDto(private val uomConverter: UnitOfMeasureToUnitOfMeasureDto) :
    Converter<Ingredient, IngredientDto> {
    override fun convert(source: Ingredient): IngredientDto? {
        return IngredientDto(
            id = source.id,
            recipeId = source.recipe?.id,
            description = source.description,
            amount = source.amount,
            unitOfMeasure = source.unitOfMeasure?.let { uomConverter.convert(it) }
        )
    }
}