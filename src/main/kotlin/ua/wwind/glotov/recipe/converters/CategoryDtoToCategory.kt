package ua.wwind.glotov.recipe.converters

import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import ua.wwind.glotov.recipe.domain.Category
import ua.wwind.glotov.recipe.dto.CategoryDto

@Component
class CategoryDtoToCategory : Converter<CategoryDto, Category> {
    override fun convert(source: CategoryDto): Category? {
        return Category(source.name).apply { id = source.id }
    }
}