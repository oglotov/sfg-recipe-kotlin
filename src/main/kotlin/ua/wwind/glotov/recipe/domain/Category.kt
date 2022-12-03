package ua.wwind.glotov.recipe.domain

import jakarta.persistence.*

@Suppress("unused", "JpaDataSourceORMInspection")
@Entity
class Category(var name: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null

    @ManyToMany(mappedBy = "categories")
    val recipes: MutableSet<Recipe> = mutableSetOf()

}