package ua.wwind.glotov.recipe.domain

import jakarta.persistence.*

@Entity
open class Recipe(
    var description: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null
    var prepTime: Int? = null
    var cookTime: Int? = null
    var servings: Int? = null
    var source: String? = null
    var url: String? = null
    var directions: String? = null

    @Lob
    var image: ByteArray? = null

    @OneToOne(cascade = [CascadeType.ALL])
    var note: Note? = null

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "recipe")
    val ingredients: MutableSet<Ingredient> = mutableSetOf()

}
