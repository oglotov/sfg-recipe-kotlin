package ua.wwind.glotov.recipe.domain

import jakarta.persistence.*

@Suppress("unused")
@Entity
class Recipe(
    var description: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null
    var prepTime: Int? = null
    var cookTime: Int? = null
    var servings: Int? = null
    var source: String? = null
    var url: String? = null
    var directions: String? = null
    @ManyToMany
    @JoinTable(
        name = "recipe_category",
        joinColumns = [JoinColumn(name = "recipe_id")],
        inverseJoinColumns = [JoinColumn(name = "category_id")]
    )
    var categories: MutableSet<Category> = mutableSetOf()

    @Lob
    var image: ByteArray? = null

    @Enumerated(value = EnumType.STRING)
    var difficulty: Difficulty? = null

    @OneToOne(cascade = [CascadeType.ALL])
    var note: Note? = null

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "recipe")
    val ingredients: MutableSet<Ingredient> = mutableSetOf()

}
