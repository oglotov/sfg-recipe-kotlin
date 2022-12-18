package ua.wwind.glotov.recipe.controllers

import jakarta.servlet.http.HttpServletResponse
import org.apache.tomcat.util.http.fileupload.IOUtils
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.multipart.MultipartFile
import ua.wwind.glotov.recipe.services.ImageService
import ua.wwind.glotov.recipe.services.RecipeService

@Controller
class ImageController(
    private val imageService: ImageService,
    private val recipeService: RecipeService,
) {
    @GetMapping("recipe/{id}/image")
    fun showUploadForm(@PathVariable id: String, model: Model): String {
        model.addAttribute("recipe", recipeService.findDtoById(id.toLong()))
        return "recipe/imageuploadform"
    }

    @PostMapping("recipe/{id}/image")
    fun handleImagePost(@PathVariable id: String, @RequestParam("imagefile") file: MultipartFile): String {
        imageService.saveImageFile(id.toLong(), file)
        return "redirect:/recipe/$id/show"
    }

    @GetMapping("recipe/{id}/recipeimage")
    fun renderImageFromDB(@PathVariable id: String, response: HttpServletResponse) {
        val recipe = recipeService.findById(id.toLong())
        response.contentType = "image/jpeg"
        recipe?.image?.let {
            IOUtils.copy(it.inputStream(), response.outputStream)
        }
    }
}