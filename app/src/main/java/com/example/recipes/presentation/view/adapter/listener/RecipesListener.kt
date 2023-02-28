package com.example.recipes.presentation.view.adapter.listener

interface RecipesListener {

    fun onElementSelected(
        title: String,
        image: String,
        summary: String,
        readyInMinutes: Int,
        aggregateLikes: Int,
        extendedIngredients: String,
        instructions: String
    )

}