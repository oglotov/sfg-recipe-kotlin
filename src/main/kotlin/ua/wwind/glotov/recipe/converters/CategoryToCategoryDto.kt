package ua.wwind.glotov.recipe.converters

import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import ua.wwind.glotov.recipe.domain.Category
import ua.wwind.glotov.recipe.dto.CategoryDto

@Component
class CategoryToCategoryDto : Converter<Category, CategoryDto> {
    override fun convert(source: Category): CategoryDto? {
        return CategoryDto(id = source.id, name = source.name)
    }
}