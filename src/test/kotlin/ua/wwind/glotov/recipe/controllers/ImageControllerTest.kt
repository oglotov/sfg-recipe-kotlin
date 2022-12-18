package ua.wwind.glotov.recipe.controllers

import io.mockk.Runs
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.just
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.TestInstance.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.mock.web.MockMultipartFile
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders.*
import ua.wwind.glotov.recipe.dto.RecipeDto
import ua.wwind.glotov.recipe.services.ImageService
import ua.wwind.glotov.recipe.services.RecipeService

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(MockKExtension::class)
class ImageControllerTest {

    @MockK
    lateinit var imageService: ImageService

    @MockK
    lateinit var recipeService: RecipeService

    @InjectMockKs
    lateinit var controller: ImageController

    private lateinit var mockMvc: MockMvc

    @BeforeAll
    fun setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    fun showUploadForm() {
        // given
        val recipeDto = RecipeDto(id = 1L)
        every { recipeService.findDtoById(any()) } returns recipeDto

        // when
        mockMvc.perform(get("/recipe/1/image"))
            .andExpect(status().isOk)
            .andExpect(model().attributeExists("recipe"))

        verify(exactly = 1) { recipeService.findDtoById(eq(1L)) }
    }

    @Test
    fun handleImagePost() {
        val multipartFile =
            MockMultipartFile("imagefile", "testing.txt", "text/plain", "Recipe Test File".byteInputStream())
        every { imageService.saveImageFile(any(), any()) } just Runs

        mockMvc.perform(multipart("/recipe/1/image").file(multipartFile))
            .andExpect(status().is3xxRedirection)
            .andExpect(header().string("Location", "/recipe/1/show"))

        verify { imageService.saveImageFile(any(), any()) }
    }
}