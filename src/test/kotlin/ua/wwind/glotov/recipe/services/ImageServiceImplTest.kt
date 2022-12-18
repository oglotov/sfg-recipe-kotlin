package ua.wwind.glotov.recipe.services

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.slot
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.mock.web.MockMultipartFile
import ua.wwind.glotov.recipe.domain.Recipe
import ua.wwind.glotov.recipe.repositories.RecipeRepository
import java.util.*

@ExtendWith(MockKExtension::class)
class ImageServiceImplTest {

    @MockK
    lateinit var recipeRepository: RecipeRepository

    @InjectMockKs
    lateinit var imageService: ImageServiceImpl

    @Test
    fun saveImageFile() {
        val recipe = Recipe("Test").apply { id = 1L }
        val multipartFile =
            MockMultipartFile("imagefile", "testing.txt", "text/plain", "Recipe Test File".byteInputStream())
        val recipeCaptor = slot<Recipe>()
        every { recipeRepository.findById(any()) } returns Optional.of(recipe)
        every { recipeRepository.save(capture(recipeCaptor)) } returnsArgument 0

        imageService.saveImageFile(1L, multipartFile)
        val savedRecipe = recipeCaptor.captured
        assertThat(savedRecipe.image!!.size).isEqualTo(multipartFile.size)

    }
}