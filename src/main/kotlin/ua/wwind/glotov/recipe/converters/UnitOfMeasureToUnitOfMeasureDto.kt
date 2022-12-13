package ua.wwind.glotov.recipe.converters

import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import ua.wwind.glotov.recipe.domain.UnitOfMeasure
import ua.wwind.glotov.recipe.dto.UnitOfMeasureDto

@Component
class UnitOfMeasureToUnitOfMeasureDto: Converter<UnitOfMeasure, UnitOfMeasureDto> {
    override fun convert(source: UnitOfMeasure): UnitOfMeasureDto? {
        return UnitOfMeasureDto(name = source.name, id = source.id)
    }

}