package ua.wwind.glotov.recipe.repositories

import org.springframework.data.repository.CrudRepository
import ua.wwind.glotov.recipe.domain.Recipe

interface RecipeRepository : CrudRepository<Recipe, Long>