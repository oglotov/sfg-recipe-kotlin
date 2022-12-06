package ua.wwind.glotov.recipe.services

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations.openMocks
import ua.wwind.glotov.recipe.domain.Recipe
import ua.wwind.glotov.recipe.repositories.RecipeRepository

class RecipeServiceImplTest {

    lateinit var recipeService: RecipeService

    @Mock
    lateinit var recipeRepository: RecipeRepository

    @BeforeEach
    fun setUp() {
        openMocks(this)
        recipeService = RecipeServiceImpl(recipeRepository)
    }

    @Test
    fun findAll() {

        val recipe = Recipe("My Recipe")
        val recipesSet = listOf(recipe)

        `when`(recipeService.findAll()).thenReturn(recipesSet)

        val recipes = recipeService.findAll()
        assertEquals(recipes.size, 1)
        verify(recipeRepository, times(1)).findAll()
    }
}