package com.example.recipes.data.recipes

import com.example.recipes.data.ApiServiceRecipes
import com.example.recipes.data.database.RecipesEntity
import com.example.recipes.data.database.dao.RecipesDAO
import com.example.recipes.domain.RecipesRepository
import com.example.recipes.model.RecipesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(
    private val apiServiceRecipes: ApiServiceRecipes,
    private val recipesDAO: RecipesDAO
) : RecipesRepository {
    override suspend fun getRecipes() {
        withContext(Dispatchers.IO) {
            recipesDAO.doesRecipesEntityExist().collect {
                if (!it) {
                    val recipesResponse = apiServiceRecipes.getRecipes()
                    recipesResponse.body()?.recipesList?.let {
                        it.map {
                            val recipesEntity = RecipesEntity(
                                Random().nextInt(),
                                it.aggregateLikes,
                                it.title,
                                it.image,
                                it.readyInMinutes,
                                it.summary,
                                it.extendedIngredients,
                                it.instructions
                            )
                            recipesDAO.insertRecipesEntity(recipesEntity)

                        }

                    }


                }


            }


        }

    }

    override suspend fun showRecipes(): Flow<List<RecipesModel>> {
        return withContext(Dispatchers.IO) {
            val recipesEntity = recipesDAO.getRecipesEntities()
            recipesEntity.map { recipesList ->
                recipesList.map { it ->
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


        }


    }

    override suspend fun findRecipeEntityByTitle(searchQuery: String): Flow<List<RecipesModel>> {
        return withContext(Dispatchers.IO) {
            val recipesEntity = recipesDAO.findRecipeEntityByTitle(searchQuery)
            recipesEntity.map { recipesList ->
                recipesList.map { it ->
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


        }


    }


}

