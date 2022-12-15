package ua.wwind.glotov.recipe.repositories

import org.springframework.data.repository.CrudRepository
import ua.wwind.glotov.recipe.domain.Ingredient
import java.util.*

interface IngredientRepository : CrudRepository<Ingredient, Long> {
    fun findByRecipeIdAndId(recipeId: Long, id: Long): Optional<Ingredient>
}