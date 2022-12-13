package ua.wwind.glotov.recipe.converters

import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component
import ua.wwind.glotov.recipe.domain.Note
import ua.wwind.glotov.recipe.dto.NoteDto

@Component
class NoteToNoteDto : Converter<Note, NoteDto> {
    override fun convert(source: Note): NoteDto? {
        return NoteDto(
            id = source.id,
            recipeNotes = source.recipeNotes
        )
    }
}