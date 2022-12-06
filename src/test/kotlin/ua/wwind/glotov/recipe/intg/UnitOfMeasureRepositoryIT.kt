package ua.wwind.glotov.recipe.intg

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import ua.wwind.glotov.recipe.repositories.UnitOfMeasureRepository

@DataJpaTest
class UnitOfMeasureRepositoryIT {

    @Autowired
    lateinit var unitOfMeasureRepository: UnitOfMeasureRepository

    @BeforeEach
    fun setUp() {

    }

    @Test
    fun findByName() {
        val result = unitOfMeasureRepository.findByName("Teaspoon")
        assertEquals("Teaspoon", result?.name)
    }

    @Test
    fun findByNameCup() {
        val result = unitOfMeasureRepository.findByName("Cup")
        assertEquals("Cup", result?.name)
    }
}