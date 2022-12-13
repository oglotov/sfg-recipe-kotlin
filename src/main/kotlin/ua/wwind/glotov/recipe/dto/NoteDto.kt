package ua.wwind.glotov.recipe.dto

import java.io.Serializable

/**
 * A DTO for the {@link ua.wwind.glotov.recipe.domain.Note} entity
 */
data class NoteDto(var recipeNotes: String? = null, var id: Long? = null) : Serializable