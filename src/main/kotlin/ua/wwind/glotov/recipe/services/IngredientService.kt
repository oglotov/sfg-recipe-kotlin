package ua.wwind.glotov.recipe.services

import ua.wwind.glotov.recipe.dto.IngredientDto

interface IngredientService {
    fun findDtoByRecipeIdAndId(recipeId: Long, id: Long): IngredientDto?
    fun saveDto(ingredientDto: IngredientDto): IngredientDto
}