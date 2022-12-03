package ua.wwind.glotov.recipe.domain

import jakarta.persistence.*

@Entity
open class Note(
    @OneToOne
    val recipe: Recipe,

    @Lob
    var recipeNotes: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null
}
