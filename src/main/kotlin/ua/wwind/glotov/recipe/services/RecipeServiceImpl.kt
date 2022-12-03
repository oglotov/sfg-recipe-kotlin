package ua.wwind.glotov.recipe.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ua.wwind.glotov.recipe.domain.Recipe
import ua.wwind.glotov.recipe.repositories.RecipeRepository

@Service
class RecipeServiceImpl @Autowired constructor(private val recipeRepository: RecipeRepository) : RecipeService {
    override fun findAll(): List<Recipe> = recipeRepository.findAll().toList()
}