package ua.wwind.glotov.recipe.converters

import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import ua.wwind.glotov.recipe.domain.Note
import ua.wwind.glotov.recipe.dto.NoteDto

@Component
class NoteDtoToNote : Converter<NoteDto, Note> {
    override fun convert(source: NoteDto): Note? {
        return Note(source.recipeNotes).apply { id = source.id }
    }
}