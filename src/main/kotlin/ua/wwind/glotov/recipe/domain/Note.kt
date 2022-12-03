package ua.wwind.glotov.recipe.domain

import jakarta.persistence.*

@Suppress("unused")
@Entity
class Note(
    @OneToOne
    val recipe: Recipe,

    @Lob
    var recipeNotes: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null
}
