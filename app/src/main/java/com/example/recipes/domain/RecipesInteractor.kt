package com.example.recipes.domain

import com.example.recipes.model.RecipesModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipesInteractor @Inject constructor
    (private val recipesRepository: RecipesRepository) {

    suspend fun getRecipes() {

        return recipesRepository.getRecipes()
    }


    suspend fun showRecipes():Flow <List<RecipesModel>> {

        return recipesRepository.showRecipes()
    }

    suspend fun findRecipe(searchQuery: String):Flow <List<RecipesModel>> {
        return recipesRepository.findRecipeEntityByTitle(searchQuery)

    }


}