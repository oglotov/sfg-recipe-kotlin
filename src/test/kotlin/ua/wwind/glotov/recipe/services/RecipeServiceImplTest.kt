package ua.wwind.glotov.recipe.services

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.*
import ua.wwind.glotov.recipe.converters.RecipeDtoToRecipe
import ua.wwind.glotov.recipe.converters.RecipeToRecipeDto
import ua.wwind.glotov.recipe.domain.Recipe
import ua.wwind.glotov.recipe.repositories.RecipeRepository

@ExtendWith(MockKExtension::class)
class RecipeServiceImplTest {

    @InjectMockKs
    private lateinit var recipeService: RecipeServiceImpl

    @MockK
    lateinit var recipeRepository: RecipeRepository

    @RelaxedMockK
    lateinit var recipeToRecipeDto: RecipeToRecipeDto

    @RelaxedMockK
    lateinit var recipeDtoToRecipe: RecipeDtoToRecipe

//    @BeforeEach
//    fun setUp() {
//        openMocks(this)
//        recipeService = RecipeServiceImpl(
//            recipeRepository,
//            recipeDtoToRecipe,
//            recipeToRecipeDto
//        )
//    }

    @Test
    fun findAll() {

        val recipe = Recipe("My Recipe")
        val recipesSet = listOf(recipe)

        every { recipeRepository.findAll() } returns recipesSet

        val recipes = recipeService.findAll()
        assertEquals(recipes.size, 1)
        io.mockk.verify(exactly = 1) { recipeRepository.findAll() }
    }
}