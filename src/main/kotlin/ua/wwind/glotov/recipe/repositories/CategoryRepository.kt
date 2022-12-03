package ua.wwind.glotov.recipe.repositories

import org.springframework.data.repository.CrudRepository
import ua.wwind.glotov.recipe.domain.Category

interface CategoryRepository : CrudRepository<Category, Long> {
    fun findByName(name: String): Category?
}