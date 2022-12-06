package ua.wwind.glotov.recipe.controllers

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentCaptor
import org.mockito.Mock
import org.mockito.Mockito.eq
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.test.web.reactive.server.expectBody
import org.springframework.ui.Model
import ua.wwind.glotov.recipe.domain.Recipe
import ua.wwind.glotov.recipe.services.RecipeService

@WebMvcTest(controllers = [IndexController::class])
@AutoConfigureWebTestClient
class IndexControllerTest {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Autowired
    lateinit var indexController: IndexController

    @MockkBean
    lateinit var recipeService: RecipeService

    @Mock
    lateinit var model: Model

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
    }

    @Test
    fun getIndexPage() {
        val recipe = Recipe("My Recipe")
        val recipes = listOf(recipe)
        every { recipeService.findAll() } returns recipes

        val result = webTestClient
            .get()
            .uri("/")
            .exchange()
            .expectStatus().isOk
            .expectBody<String>()
            .returnResult()
            .responseBody
    }

    @Test
    fun getIndexPage2() {
        val recipe = Recipe("My Recipe")
        val recipes = listOf(recipe)
        every { recipeService.findAll() } returns recipes

        val argumentCaptor: ArgumentCaptor<List<*>> = ArgumentCaptor.forClass(List::class.java)

        val result = indexController.getIndexPage(model)

        assertEquals(result, "index")
        verify(model).addAttribute(eq("recipes"), argumentCaptor.capture())
        val listResult: List<Recipe> = argumentCaptor.value as List<Recipe>
        assertEquals(listResult, recipes)

    }
}