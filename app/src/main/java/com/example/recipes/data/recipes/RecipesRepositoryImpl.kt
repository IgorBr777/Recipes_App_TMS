package com.example.recipes.data.recipes

import com.example.recipes.data.ApiServiceRecipes
import com.example.recipes.data.database.RecipesEntity
import com.example.recipes.data.database.dao.RecipesDAO
import com.example.recipes.domain.RecipesRepository
import com.example.recipes.model.RecipesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(
    private val apiServiceRecipes: ApiServiceRecipes,
    private val recipesDAO: RecipesDAO
) : RecipesRepository {
    override suspend fun getRecipes() {
        return withContext(Dispatchers.IO) {
            if (!recipesDAO.doesRecipesEntityExist()) {
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

    override suspend fun showRecipes(): List<RecipesModel> {
        return withContext(Dispatchers.IO) {
            val recipesEntity = recipesDAO.getRecipesEntities()
            recipesEntity.map {

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

    override suspend fun findRecipeEntityByTitle(searchText: String): kotlin.collections.List<RecipesModel> {
        return withContext(Dispatchers.IO) {
            val recipesEntity = recipesDAO.findRecipeEntityByTitle(searchText)
            recipesEntity.map {
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

