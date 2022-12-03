package ua.wwind.glotov.recipe.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class IndexController {

    @GetMapping("/", "", "/index")
    fun getIndexPage(): String {
        return "index"
    }
}