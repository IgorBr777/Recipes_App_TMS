package com.example.recipes.presentation.view.adapter.listener

interface RecipesListener {
    fun onClick()

    fun onElementSelected(title: String, image: String)

}