package com.example.recipes.data.model

   data class RecipeDetailsResponse(
    val id: Int,
    val title: String,
    val image: String,
    val readyInMinutes:Int,
    val aggregateLikes: Int,
    val summary: String,
    val extendedIngredients: List<ExtendedIngredient>

) {
    data class ExtendedIngredient(
        val id: Int,
        val image: String,
        val original: String,
        val name: String,
        val amount: Double

    )
}
