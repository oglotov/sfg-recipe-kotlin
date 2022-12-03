package ua.wwind.glotov.recipe.repositories

import org.springframework.data.repository.CrudRepository
import ua.wwind.glotov.recipe.domain.Category
import ua.wwind.glotov.recipe.domain.UnitOfMeasure

interface CategoryRepository : CrudRepository<Category, Long>