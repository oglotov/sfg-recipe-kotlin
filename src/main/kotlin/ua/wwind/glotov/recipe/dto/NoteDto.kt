package ua.wwind.glotov.recipe.dto

import java.io.Serializable

/**
 * A DTO for the {@link ua.wwind.glotov.recipe.domain.Note} entity
 */
data class NoteDto(val recipeNotes: String, val id: Long? = null) : Serializable