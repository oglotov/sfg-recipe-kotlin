package ua.wwind.glotov.recipe.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import ua.wwind.glotov.recipe.services.RecipeService

@Controller
class RecipeController @Autowired constructor(private val recipeService: RecipeService) {
    @GetMapping("/recipe/show/{recipe_id}")
    fun showRecipe(model: Model, @PathVariable("recipe_id") recipeId: String): String {
        model.addAttribute("recipe", recipeService.findById(recipeId.toLong())!!)
        return "recipe/show"
    }
}