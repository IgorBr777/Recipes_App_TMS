package com.example.recipes.data.model

import com.google.gson.annotations.SerializedName

data class RecipesResponse(

    @SerializedName("recipes")
    val recipesList: List<Recipes>

)

data class Recipes(
    val aggregateLikes: Int,
    val id: Int,
    val title: String,
    val readyInMinutes: Int,
    val image: String,
    val imageType: String,
    val summary: String,
    val extendedIngredients: String,
    val instructions: String

)
