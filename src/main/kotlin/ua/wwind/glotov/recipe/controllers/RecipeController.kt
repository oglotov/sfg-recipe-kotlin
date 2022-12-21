package ua.wwind.glotov.recipe.controllers

import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import ua.wwind.glotov.recipe.dto.RecipeDto
import ua.wwind.glotov.recipe.exceptions.NotFoundException
import ua.wwind.glotov.recipe.services.RecipeService

@Controller
class RecipeController @Autowired constructor(private val recipeService: RecipeService) {

    companion object {
        const val RECIPE_RECIPEFORM_URL = "recipe/recipeform"
    }

    @GetMapping("/recipe/{recipe_id}/show")
    fun showRecipe(model: Model, @PathVariable("recipe_id") recipeId: Long): String {
        val recipe = recipeService.findById(recipeId) ?: throw NotFoundException("Recipe not found by id $recipeId")
        model.addAttribute("recipe", recipe)
        return "recipe/show"
    }
    @GetMapping("/recipe/{recipe_id}/delete")
    fun deleteRecipe(@PathVariable("recipe_id") recipeId: String, model: Model): String {
        recipeService.deleteById(recipeId.toLong())
        return "redirect:/"
    }

    @RequestMapping("/recipe/new")
    fun newRecipe(model: Model): String {
        model.addAttribute("recipe", RecipeDto())
        return RECIPE_RECIPEFORM_URL
    }

    @RequestMapping("/recipe/{recipeId}/update")
    fun updateRecipe(@PathVariable("recipeId") recipeId: String, model: Model): String {
        val dto = recipeService.findDtoById(recipeId.toLong()) ?: return "redirect:/recipe/new"
        model.addAttribute("recipe", dto)
        return RECIPE_RECIPEFORM_URL
    }

    @PostMapping("/recipe")
    fun saveOrUpdate(@Valid @ModelAttribute("recipe") recipe: RecipeDto, bindingResult: BindingResult): String {

        if (bindingResult.hasErrors()) {
            return RECIPE_RECIPEFORM_URL
        }

        val savedDto = recipeService.saveRecipeDto(recipe)
        return "redirect:/recipe/${savedDto.id}/show"
    }

}