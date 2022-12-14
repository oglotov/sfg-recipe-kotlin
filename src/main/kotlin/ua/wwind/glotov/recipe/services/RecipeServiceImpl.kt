package ua.wwind.glotov.recipe.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ua.wwind.glotov.recipe.converters.RecipeDtoToRecipe
import ua.wwind.glotov.recipe.converters.RecipeToRecipeDto
import ua.wwind.glotov.recipe.domain.Recipe
import ua.wwind.glotov.recipe.dto.RecipeDto
import ua.wwind.glotov.recipe.repositories.RecipeRepository
import kotlin.jvm.optionals.getOrNull

@Service
class RecipeServiceImpl @Autowired constructor(
    private val recipeRepository: RecipeRepository,
    private val recipeDtoToRecipe: RecipeDtoToRecipe,
    private val recipeToRecipeDto: RecipeToRecipeDto
) : RecipeService {
    override fun findAll(): List<Recipe> = recipeRepository.findAll().toList()

    @OptIn(ExperimentalStdlibApi::class)
    override fun findById(id: Long): Recipe? = recipeRepository.findById(id).getOrNull()
    override fun saveRecipeDto(recipeDto: RecipeDto): RecipeDto {
        val recipe = recipeDtoToRecipe.convert(recipeDto) ?: throw NullPointerException("Conversion error")
        return recipeToRecipeDto.convert(recipeRepository.save(recipe)) ?: throw RuntimeException("Unknown error with converting recipe")
    }

    @OptIn(ExperimentalStdlibApi::class)
    override fun findDtoById(id: Long): RecipeDto? {
        val recipe = recipeRepository.findById(id).getOrNull() ?: return null
        return recipeToRecipeDto.convert(recipe)
    }

    override fun deleteById(id: Long) {
        recipeRepository.deleteById(id)
    }
}