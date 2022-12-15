package ua.wwind.glotov.recipe.controllers

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import ua.wwind.glotov.recipe.dto.IngredientDto
import ua.wwind.glotov.recipe.services.IngredientService
import ua.wwind.glotov.recipe.services.RecipeService
import ua.wwind.glotov.recipe.services.UnitOfMeasureService

@Controller
class IngredientController(
    private val recipeService: RecipeService,
    private val ingredientService: IngredientService,
    private val unitOfMeasureService: UnitOfMeasureService
) {
    @GetMapping("/recipe/{recipeId}/ingredients")
    fun listIngredients(@PathVariable recipeId: String, model: Model): String {
        model.addAttribute("recipe", recipeService.findDtoById(recipeId.toLong()))
        return "recipe/ingredient/list"
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{id}/show")
    fun showIngredient(@PathVariable recipeId: String, @PathVariable id: String, model: Model): String {
        model.addAttribute(
            "ingredient",
            ingredientService.findDtoByRecipeIdAndId(recipeId = recipeId.toLong(), id = id.toLong())
        )
        return "recipe/ingredient/show"
    }
    @RequestMapping("/recipe/{recipeId}/ingredient/{id}/update")
    fun editIngredient(@PathVariable recipeId: String, @PathVariable id: String, model: Model): String {
        model.addAttribute(
            "ingredient",
            ingredientService.findDtoByRecipeIdAndId(recipeId = recipeId.toLong(), id = id.toLong())
        )
        model.addAttribute(
            "uomList",
            unitOfMeasureService.findDtoAll()
        )
        return "recipe/ingredient/ingredientform"
    }
    @RequestMapping("/recipe/{recipeId}/ingredient/{id}/delete")
    fun deleteIngredient(@PathVariable recipeId: String, @PathVariable id: String, model: Model): String {
        ingredientService.deleteById(id.toLong())
        return "redirect:/recipe/${recipeId}/ingredients"
    }
    @RequestMapping("/recipe/{recipeId}/ingredient/new")
    fun newIngredient(@PathVariable recipeId: String, model: Model): String {
        model.addAttribute(
            "ingredient",
            IngredientDto(recipeId = recipeId.toLong())
        )
        model.addAttribute(
            "uomList",
            unitOfMeasureService.findDtoAll()
        )
        return "recipe/ingredient/ingredientform"
    }

    @PostMapping("/recipe/{recipeId}/ingredient")
    fun updateIngredient(@ModelAttribute ingredientDto: IngredientDto): String {
        val savedDto = ingredientService.saveDto(ingredientDto)
        return "redirect:/recipe/${savedDto.recipeId}/ingredient/${savedDto.id}/show"
    }

}