package ua.wwind.glotov.recipe.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView
import ua.wwind.glotov.recipe.dto.RecipeDto
import ua.wwind.glotov.recipe.exceptions.NotFoundException
import ua.wwind.glotov.recipe.services.RecipeService

@Controller
class RecipeController @Autowired constructor(private val recipeService: RecipeService) {
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
        return "recipe/recipeform"
    }

    @RequestMapping("/recipe/{recipeId}/update")
    fun updateRecipe(@PathVariable("recipeId") recipeId: String, model: Model): String {
        val dto = recipeService.findDtoById(recipeId.toLong()) ?: return "redirect:/recipe/new"
        model.addAttribute("recipe", dto)
        return "recipe/recipeform"
    }

    @PostMapping("/recipe")
    fun saveOrUpdate(@ModelAttribute recipeDto: RecipeDto): String {
        val savedDto = recipeService.saveRecipeDto(recipeDto)
        return "redirect:/recipe/${savedDto.id}/show"
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException::class)
    fun handleNotFound(ex: Exception): ModelAndView {
        val modelAndView = ModelAndView()
        modelAndView.viewName = "404error"
        modelAndView.addObject("exception", ex)
        return modelAndView
    }

}