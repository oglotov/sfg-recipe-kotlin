package ua.wwind.glotov.recipe.services

import org.springframework.web.multipart.MultipartFile

interface ImageService {
    fun saveImageFile(recipeId: Long, file: MultipartFile)
}