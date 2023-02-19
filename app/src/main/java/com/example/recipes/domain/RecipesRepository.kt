package com.example.recipes.domain

import com.example.recipes.model.RecipesModel

interface RecipesRepository {

    suspend fun getRecipes():List<RecipesModel>

}