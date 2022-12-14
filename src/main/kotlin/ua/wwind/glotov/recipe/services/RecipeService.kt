package ua.wwind.glotov.recipe.services

import ua.wwind.glotov.recipe.domain.Recipe
import ua.wwind.glotov.recipe.dto.RecipeDto

interface RecipeService {
    fun findAll(): List<Recipe>
    fun findById(id: Long): Recipe?
    fun saveRecipeDto(recipeDto: RecipeDto): RecipeDto
    fun findDtoById(id: Long): RecipeDto?
    fun deleteById(id: Long)
}