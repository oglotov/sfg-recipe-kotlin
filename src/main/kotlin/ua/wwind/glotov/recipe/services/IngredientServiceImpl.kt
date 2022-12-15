package ua.wwind.glotov.recipe.services

import org.springframework.stereotype.Service
import ua.wwind.glotov.recipe.converters.IngredientToIngredientDto
import ua.wwind.glotov.recipe.dto.IngredientDto
import ua.wwind.glotov.recipe.repositories.IngredientRepository
import kotlin.jvm.optionals.getOrNull

@OptIn(ExperimentalStdlibApi::class)
@Service
class IngredientServiceImpl(
    private val ingredientRepository: IngredientRepository,
    private val ingredientToIngredientDto: IngredientToIngredientDto
) : IngredientService {
    override fun findDtoByRecipeIdAndId(recipeId: Long, id: Long): IngredientDto? =
        ingredientToIngredientDto.convert(ingredientRepository.findByRecipeIdAndId(recipeId, id).getOrNull()!!)
}