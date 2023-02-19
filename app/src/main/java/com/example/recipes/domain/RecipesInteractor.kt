package com.example.recipes.domain

import com.example.recipes.model.RecipesModel
import javax.inject.Inject

class RecipesInteractor @Inject constructor
    (private val recipesRepository: RecipesRepository) {

    suspend fun getRecipes():List<RecipesModel>{

        return recipesRepository.getRecipes()
    }

}