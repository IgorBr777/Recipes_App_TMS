package com.example.recipes.domain

import com.example.recipes.model.RecipesModel
import kotlinx.coroutines.flow.Flow

interface RecipesRepository {

    suspend fun getRecipes()

    suspend fun showRecipes():Flow <List<RecipesModel>>

    suspend fun findRecipeEntityByTitle(searchQuery: String): Flow<List<RecipesModel>>

}