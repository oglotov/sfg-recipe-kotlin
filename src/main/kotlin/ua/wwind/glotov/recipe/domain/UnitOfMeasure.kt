package ua.wwind.glotov.recipe.domain

import jakarta.persistence.*

@Suppress("unused")
@Entity
class UnitOfMeasure(
    var uom: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null
}
