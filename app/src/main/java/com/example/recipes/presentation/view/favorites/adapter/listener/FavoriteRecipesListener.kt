package com.example.recipes.presentation.view.favorites.adapter.listener

interface FavoriteRecipesListener {
    fun onFavoriteElementSelected(
        title: String,
        image: String,
        summary: String,
        readyInMinutes: Int,
        aggregateLikes: Int,
        extendedIngredients: String,
        instructions: String
    )

fun onFavDeleteClicked(title: String)



}