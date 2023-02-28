package com.example.recipes.data.recipes

import com.example.recipes.data.ApiServiceRecipes
import com.example.recipes.domain.RecipesRepository
import com.example.recipes.model.RecipesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(
    private val apiServiceRecipes: ApiServiceRecipes
) : RecipesRepository {
    override suspend fun getRecipes(): List<RecipesModel> {
        return withContext(Dispatchers.IO) {
            val recipesResponse = apiServiceRecipes.getRecipes()
            recipesResponse.body()?.recipesList?.let {
                it.map {
                    RecipesModel(
                        it.id,
                        it.title,
                        it.image,
                        it.readyInMinutes,
                        it.aggregateLikes,
                        it.summary,
                        it.extendedIngredients,
                        it.instructions
                    )
                }

            }

        } ?: kotlin.run {
            emptyList()
        }

    }
}

