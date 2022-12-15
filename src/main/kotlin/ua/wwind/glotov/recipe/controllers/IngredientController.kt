package ua.wwind.glotov.recipe.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import ua.wwind.glotov.recipe.services.RecipeService

@Controller
class IngredientController(private val recipeService: RecipeService) {
    @GetMapping("/recipe/{recipeId}/ingredients")
    fun listIngredients(@PathVariable recipeId: String, model: Model): String {
        model.addAttribute("recipe", recipeService.findDtoById(recipeId.toLong()))
        return "recipe/ingredient/list"
    }
}