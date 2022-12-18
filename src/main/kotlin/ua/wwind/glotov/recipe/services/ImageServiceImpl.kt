package ua.wwind.glotov.recipe.services

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import ua.wwind.glotov.recipe.repositories.RecipeRepository

@Service
class ImageServiceImpl(private val recipeRepository: RecipeRepository) : ImageService {
    override fun saveImageFile(recipeId: Long, file: MultipartFile) {
        try {
            val recipe = recipeRepository.findById(recipeId).get()
            recipe.image = file.bytes
            recipeRepository.save(recipe)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}