package ua.wwind.glotov.recipe.repositories

import org.springframework.data.repository.CrudRepository
import ua.wwind.glotov.recipe.domain.UnitOfMeasure

interface UnitOfMeasureRepository : CrudRepository<UnitOfMeasure, Long> {
    fun findByName(name: String): UnitOfMeasure?
}