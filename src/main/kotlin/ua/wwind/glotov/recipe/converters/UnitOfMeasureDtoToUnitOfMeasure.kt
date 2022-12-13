package ua.wwind.glotov.recipe.converters

import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import ua.wwind.glotov.recipe.domain.UnitOfMeasure
import ua.wwind.glotov.recipe.dto.UnitOfMeasureDto

@Component
class UnitOfMeasureDtoToUnitOfMeasure: Converter<UnitOfMeasureDto, UnitOfMeasure> {
    override fun convert(source: UnitOfMeasureDto): UnitOfMeasure? {
        val uom = UnitOfMeasure(source.name ?: "").apply {
            id = source.id
        }
        return uom
    }

}