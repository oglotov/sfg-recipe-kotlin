package ua.wwind.glotov.recipe.bootstrap

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import ua.wwind.glotov.recipe.domain.*
import ua.wwind.glotov.recipe.repositories.CategoryRepository
import ua.wwind.glotov.recipe.repositories.RecipeRepository
import ua.wwind.glotov.recipe.repositories.UnitOfMeasureRepository
import java.math.BigDecimal


@Component
class RecipeBootstrap @Autowired constructor(
    private val recipeRepository: RecipeRepository,
    private val unitOfMeasureRepository: UnitOfMeasureRepository,
    private val categoryRepository: CategoryRepository
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        loadData()
    }

    private fun loadData() {
        recipeRepository.saveAll(getRecipeList())
    }

    private fun getRecipeList(): List<Recipe> {
        val recipes: MutableList<Recipe> = mutableListOf()
        //get UOMs
        val eachUom: UnitOfMeasure = unitOfMeasureRepository.findByName("Each")
            ?: throw RuntimeException("Expected UOM Not Found")

        val tableSpoonUom: UnitOfMeasure = unitOfMeasureRepository.findByName("Tablespoon")
            ?: throw RuntimeException("Expected UOM Not Found")

        val teaSpoonUom: UnitOfMeasure = unitOfMeasureRepository.findByName("Teaspoon")
            ?: throw RuntimeException("Expected UOM Not Found")

        val dashUom: UnitOfMeasure = unitOfMeasureRepository.findByName("Dash")
            ?: throw RuntimeException("Expected UOM Not Found")

        val pintUom: UnitOfMeasure = unitOfMeasureRepository.findByName("Pint")
            ?: throw RuntimeException("Expected UOM Not Found")

        val cupsUom: UnitOfMeasure = unitOfMeasureRepository.findByName("Cup")
            ?: throw RuntimeException("Expected UOM Not Found")

        //get Categories

        //get Categories
        val americanCategory: Category = categoryRepository.findByName("American")
            ?: throw RuntimeException("Expected Category Not Found")

        val mexicanCategory: Category = categoryRepository.findByName("Mexican")
            ?: throw RuntimeException("Expected Category Not Found")

        //Yummy Guac
        val guacRecipe = Recipe("Perfect Guacamole")
            .apply {
                prepTime = 10
                cookTime = 0
                difficulty = Difficulty.EASY
                directions =
                    "1 Cut avocado, remove flesh: Cut the avocados in half. Remove seed. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon" +
                            "\n" +
                            "2 Mash with a fork: Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
                            "\n" +
                            "3 Add salt, lime juice, and the rest: Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                            "Add the chopped onion, cilantro, black pepper, and chiles. Chili peppers vary individually in their hotness. So, start with a half of one chili pepper and add to the guacamole to your desired degree of hotness.\n" +
                            "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                            "4 Cover with plastic and chill to store: Place plastic wrap on the surface of the guacamole cover it and to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.) Refrigerate until ready to serve.\n" +
                            "Chilling tomatoes hurts their flavor, so if you want to add chopped tomato to your guacamole, add it just before serving.\n" +
                            "\n" +
                            "\n" + "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvpiV9Sd"
                note = Note(
                    "For a very quick guacamole just take a 1/4 cup of salsa and mix it in with your mashed avocados.\n" +
                            "Feel free to experiment! One classic Mexican guacamole has pomegranate seeds and chunks of peaches in it (a Diana Kennedy favorite). Try guacamole with added pineapple, mango, or strawberries.\n" +
                            "The simplest version of guacamole is just mashed avocados with salt. Don't let the lack of availability of other ingredients stop you from making guacamole.\n" +
                            "To extend a limited supply of avocados, add either sour cream or cottage cheese to your guacamole dip. Purists may be horrified, but so what? It tastes great.\n" +
                            "\n" +
                            "\n" +
                            "Read more: http://www.simplyrecipes.com/recipes/perfect_guacamole/#ixzz4jvoun5ws"
                )
                addIngredient(Ingredient("ripe avocados", BigDecimal(2), eachUom))
                addIngredient(Ingredient("Kosher salt", BigDecimal(".5"), teaSpoonUom))
                addIngredient(
                    Ingredient(
                        "fresh lime juice or lemon juice",
                        BigDecimal(2),
                        tableSpoonUom
                    )
                )
                addIngredient(
                    Ingredient(
                        "minced red onion or thinly sliced green onion",
                        BigDecimal(2),
                        tableSpoonUom
                    )
                )
                addIngredient(
                    Ingredient(
                        "serrano chiles, stems and seeds removed, minced",
                        BigDecimal(2),
                        eachUom
                    )
                )
                addIngredient(Ingredient("Cilantro", BigDecimal(2), tableSpoonUom))
                addIngredient(Ingredient("freshly grated black pepper", BigDecimal(2), dashUom))
                addIngredient(
                    Ingredient(
                        "ripe tomato, seeds and pulp removed, chopped",
                        BigDecimal(".5"),
                        eachUom
                    )
                )

                categories.add(americanCategory)
                categories.add(mexicanCategory)
            }

        recipes.add(guacRecipe)

        //Yummy Tacos
        val tacosRecipe = Recipe("Spicy Grilled Chicken Taco")
            .apply {
                cookTime = 9
                prepTime = 20
                difficulty = Difficulty.MODERATE

                directions = ("1 Prepare a gas or charcoal grill for medium-high, direct heat.\n" +
                        "2 Make the marinade and coat the chicken: In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                        "Set aside to marinate while the grill heats and you prepare the rest of the toppings.\n" +
                        "\n" +
                        "\n" +
                        "3 Grill the chicken: Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165F. Transfer to a plate and rest for 5 minutes.\n" +
                        "4 Warm the tortillas: Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                        "Wrap warmed tortillas in a tea towel to keep them warm until serving.\n" +
                        "5 Assemble the tacos: Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n" +
                        "\n" +
                        "\n" +
                        "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvtrAnNm")

                note = Note(
                    "We have a family motto and it is this: Everything goes better in a tortilla.\n" +
                            "Any and every kind of leftover can go inside a warm tortilla, usually with a healthy dose of pickled jalapenos. I can always sniff out a late-night snacker when the aroma of tortillas heating in a hot pan on the stove comes wafting through the house.\n" +
                            "Today’s tacos are more purposeful – a deliberate meal instead of a secretive midnight snack!\n" +
                            "First, I marinate the chicken briefly in a spicy paste of ancho chile powder, oregano, cumin, and sweet orange juice while the grill is heating. You can also use this time to prepare the taco toppings.\n" +
                            "Grill the chicken, then let it rest while you warm the tortillas. Now you are ready to assemble the tacos and dig in. The whole meal comes together in about 30 minutes!\n" +
                            "\n" +
                            "\n" +
                            "Read more: http://www.simplyrecipes.com/recipes/spicy_grilled_chicken_tacos/#ixzz4jvu7Q0MJ"
                )
                addIngredient(Ingredient("Ancho Chili Powder", BigDecimal(2), tableSpoonUom))
                addIngredient(Ingredient("Dried Oregano", BigDecimal(1), teaSpoonUom))
                addIngredient(Ingredient("Dried Cumin", BigDecimal(1), teaSpoonUom))
                addIngredient(Ingredient("Sugar", BigDecimal(1), teaSpoonUom))
                addIngredient(Ingredient("Salt", BigDecimal(".5"), teaSpoonUom))
                addIngredient(Ingredient("Clove of Garlic, Choppedr", BigDecimal(1), eachUom))
                addIngredient(Ingredient("finely grated orange zestr", BigDecimal(1), tableSpoonUom))
                addIngredient(
                    Ingredient(
                        "fresh-squeezed orange juice",
                        BigDecimal(3),
                        tableSpoonUom
                    )
                )
                addIngredient(Ingredient("Olive Oil", BigDecimal(2), tableSpoonUom))
                addIngredient(Ingredient("boneless chicken thighs", BigDecimal(4), tableSpoonUom))
                addIngredient(Ingredient("small corn tortillasr", BigDecimal(8), eachUom))
                addIngredient(Ingredient("packed baby arugula", BigDecimal(3), cupsUom))
                addIngredient(Ingredient("medium ripe avocados, slic", BigDecimal(2), eachUom))
                addIngredient(Ingredient("radishes, thinly sliced", BigDecimal(4), eachUom))
                addIngredient(Ingredient("cherry tomatoes, halved", BigDecimal(".5"), pintUom))
                addIngredient(Ingredient("red onion, thinly sliced", BigDecimal(".25"), eachUom))
                addIngredient(Ingredient("Roughly chopped cilantro", BigDecimal(4), eachUom))
                addIngredient(
                    Ingredient(
                        "cup sour cream thinned with 1/4 cup milk",
                        BigDecimal(4),
                        cupsUom
                    )
                )
                addIngredient(Ingredient("lime, cut into wedges", BigDecimal(4), eachUom))

                categories.add(americanCategory)
                categories.add(mexicanCategory)
            }

        recipes.add(tacosRecipe)
        return recipes
    }
}