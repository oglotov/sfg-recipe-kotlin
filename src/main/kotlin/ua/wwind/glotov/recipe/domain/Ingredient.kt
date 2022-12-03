package ua.wwind.glotov.recipe.domain

import jakarta.persistence.*
import java.math.BigDecimal

@Suppress("unused")
@Entity
class Ingredient(
    var description: String,
    var amount: BigDecimal,
    @ManyToOne(fetch = FetchType.EAGER)
    var unitOfMeasure: UnitOfMeasure,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null
    @ManyToOne
    var recipe: Recipe? = null
}
