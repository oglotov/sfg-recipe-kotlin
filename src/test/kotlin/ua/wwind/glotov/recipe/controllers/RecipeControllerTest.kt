package ua.wwind.glotov.recipe.controllers

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import ua.wwind.glotov.recipe.domain.Recipe
import ua.wwind.glotov.recipe.dto.RecipeDto
import ua.wwind.glotov.recipe.services.RecipeService

@ExtendWith(MockKExtension::class)
@WebMvcTest(controllers = [RecipeController::class])
@AutoConfigureWebTestClient
class RecipeControllerTest {
    @Autowired
    lateinit var webTestClient: WebTestClient

    @Autowired
    lateinit var recipeController: RecipeController

    @MockkBean
    lateinit var recipeService: RecipeService

//    @MockK
//    lateinit var model: Model

    @Test
    fun getRecipePage() {
        val recipe = Recipe("My Recipe")
        every { recipeService.findById(any()) } returns recipe

        val result = webTestClient
            .get()
            .uri("/recipe/show/1")
            .exchange()
            .expectStatus().isOk
            .expectBody<String>()
            .returnResult()
            .responseBody
        verify(exactly = 1) { recipeService.findById(eq(1L)) }
    }

    @Test
    fun updateRecipe() {
        val recipe = RecipeDto(description = "My Recipe")
        every { recipeService.findDtoById(any()) } returns recipe

        val result = webTestClient
            .get()
            .uri("/recipe/update/1")
            .exchange()
            .expectStatus().isOk
            .expectBody<String>()
            .returnResult()
            .responseBody
        verify(exactly = 1) { recipeService.findDtoById(eq(1L)) }
    }

}