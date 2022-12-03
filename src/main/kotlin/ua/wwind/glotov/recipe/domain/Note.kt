package ua.wwind.glotov.recipe.domain

import jakarta.persistence.*

@Suppress("unused")
@Entity
class Note(
    @Lob
    var recipeNotes: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null
    @OneToOne
    var recipe: Recipe? = null
}
