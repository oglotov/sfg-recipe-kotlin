package ua.wwind.glotov.recipe.controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import ua.wwind.glotov.recipe.services.RecipeService

@Controller
class IndexController @Autowired constructor(
    private val recipeService: RecipeService,
) {

    @GetMapping("/", "", "/index")
    fun getIndexPage(model: Model): String {
        model.addAttribute("recipes", recipeService.findAll())
        return "index"
    }
}