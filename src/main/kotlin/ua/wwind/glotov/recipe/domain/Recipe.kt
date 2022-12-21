package ua.wwind.glotov.recipe.domain

import jakarta.persistence.*
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.URL

@Suppress("unused")
@Entity
class Recipe(
    @NotBlank
    @Size(min = 3, max = 255)
    var description: String,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @Min(1)
    @Max(999)
    var prepTime: Int? = null

    @Min(1)
    @Max(999)
    var cookTime: Int? = null

    @Min(1)
    @Max(100)
    var servings: Int? = null
    var source: String? = null

    @URL
    var url: String? = null

    @Lob
    @NotBlank
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
        set(value) {
            if (value != null) value.recipe = this
            field = value
        }

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "recipe")
    val ingredients: MutableSet<Ingredient> = mutableSetOf()

    fun addIngredient(ingredient: Ingredient): Recipe {
        ingredient.recipe = this
        ingredients.add(ingredient)
        return this
    }

}
