package ua.wwind.glotov.recipe.services

import ua.wwind.glotov.recipe.dto.UnitOfMeasureDto

interface UnitOfMeasureService {
    fun findDtoAll(): List<UnitOfMeasureDto>
}