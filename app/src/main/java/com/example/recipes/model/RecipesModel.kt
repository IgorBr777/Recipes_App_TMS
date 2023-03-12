package com.example.recipes.model

data class RecipesModel(
    val id: Int,
    val title: String,
    val image: String,
    val readyInMinutes: Int,
    val aggregateLikes: Int,
    val summary: String,
    val extendedIngredients: String,
    val instructions: String,
    val isFavorite:Boolean
    )



