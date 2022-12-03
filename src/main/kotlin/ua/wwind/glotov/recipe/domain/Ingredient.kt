package ua.wwind.glotov.recipe.domain

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
open class Ingredient(
    var description: String,
    var amount: BigDecimal,
    @ManyToOne
    val recipe: Recipe,
    @ManyToOne(fetch = FetchType.EAGER)
    var unitOfMeasure: UnitOfMeasure
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null
}
