package ua.wwind.glotov.recipe.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CategoryTest {

    private lateinit var category: Category

    @BeforeEach
    fun setUp() {
        category = Category("Test")
    }

    @Test
    fun getId() {
        category.id = 4L
        assertEquals(4L, category.id)
    }

    @Test
    fun getRecipes() {
    }

    @Test
    fun getName() {
    }
}