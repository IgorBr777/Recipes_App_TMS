package com.example.recipes.domain

import com.example.recipes.model.FavoriteRecipesModel
import com.example.recipes.model.RecipesModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipesInteractor @Inject constructor
    (private val recipesRepository: RecipesRepository) {

    suspend fun getRecipes() {

        return recipesRepository.getRecipes()
    }


    suspend fun showRecipes(): Flow<List<RecipesModel>> {

        return recipesRepository.showRecipes()
    }

    suspend fun findRecipe(searchQuery: String): Flow<List<RecipesModel>> {
        return recipesRepository.findRecipeByTitle(searchQuery)

    }

    suspend fun getFavoriteRecipes():Flow <List<FavoriteRecipesModel>> {

        return recipesRepository.getFavoriteRecipes()

    }


    suspend fun onFavClicked(title: String, isFavorite:Boolean) {
        val foundRecipe = recipesRepository.findRecipeByTitle(title)
        recipesRepository.favClicked(foundRecipe, isFavorite)
    }


    suspend fun deleteRecipeFavoriteByTitle(title: String){
        recipesRepository.deleteRecipeFavoriteByTitle(title)
    }

}