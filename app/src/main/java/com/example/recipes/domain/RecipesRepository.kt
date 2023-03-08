package com.example.recipes.domain

import com.example.recipes.model.FavoriteRecipesModel
import com.example.recipes.model.RecipesModel
import kotlinx.coroutines.flow.Flow

interface RecipesRepository {

    suspend fun getRecipes()

    suspend fun showRecipes(): Flow<List<RecipesModel>>

    suspend fun findRecipeByTitle(searchQuery: String): Flow<List<RecipesModel>>

    suspend fun favClicked(recipesModel: Flow<List<RecipesModel>>,isFavorite:Boolean)

    suspend fun getFavoriteRecipes(): Flow<List<FavoriteRecipesModel>>

    suspend fun deleteRecipeFavoriteByTitle(title: String)

    suspend fun setDarkTheme (isEnable:Boolean)

}