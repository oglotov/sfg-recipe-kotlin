package ua.wwind.glotov.recipe.services

import ua.wwind.glotov.recipe.domain.Recipe

interface RecipeService {
    fun findAll(): List<Recipe>
    fun findById(id: Long): Recipe?
}