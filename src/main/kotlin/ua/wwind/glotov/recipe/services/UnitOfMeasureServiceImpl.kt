package ua.wwind.glotov.recipe.services

import org.springframework.stereotype.Service
import ua.wwind.glotov.recipe.converters.UnitOfMeasureToUnitOfMeasureDto
import ua.wwind.glotov.recipe.dto.UnitOfMeasureDto
import ua.wwind.glotov.recipe.repositories.UnitOfMeasureRepository

@Service
class UnitOfMeasureServiceImpl(
    private val unitOfMeasureRepository: UnitOfMeasureRepository,
    private val unitOfMeasureToUnitOfMeasureDto: UnitOfMeasureToUnitOfMeasureDto
) : UnitOfMeasureService {
    override fun findDtoAll(): List<UnitOfMeasureDto> {
        val uomList = unitOfMeasureRepository.findAll()
        return uomList.mapNotNull { unitOfMeasureToUnitOfMeasureDto.convert(it) }.toList()
    }
}