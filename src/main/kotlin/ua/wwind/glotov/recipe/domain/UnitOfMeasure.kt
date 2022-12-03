package ua.wwind.glotov.recipe.domain

import jakarta.persistence.*

@Entity
open class UnitOfMeasure(
    var uom: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    open var id: Long? = null
}
