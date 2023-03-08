package com.example.recipes.data.recipes

import com.example.recipes.data.ApiServiceRecipes
import com.example.recipes.data.database.FavoritesEntity
import com.example.recipes.data.database.RecipesEntity
import com.example.recipes.data.database.dao.RecipesDAO
import com.example.recipes.data.network.InternetConnection
import com.example.recipes.data.sharedpref.SharedPreferenceHelper
import com.example.recipes.domain.RecipesRepository
import com.example.recipes.model.FavoriteRecipesModel
import com.example.recipes.model.RecipesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.*
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(
    private val internetConnection: InternetConnection,
    private val sharedPreferenceHelper: SharedPreferenceHelper,
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
                        it.instructions,
                        it.isFavorite ?: false
                    )
                }
            }
        }
    }

    override suspend fun findRecipeByTitle(searchQuery: String): Flow<List<RecipesModel>> {
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
                        it.instructions,
                        it.isFavorite ?: false
                    )
                }
            }
        }
    }

    override suspend fun favClicked(recipesModel: Flow<List<RecipesModel>>, isFavorite: Boolean) {
        return withContext(Dispatchers.IO) {
            recipesModel.map { it ->
                it.map {
                    recipesDAO.addFavorite(it.title, isFavorite)
                }
            }
            recipesModel.collect { recipesModel ->
                recipesModel.map { recipe ->
                    recipesDAO.insertFavoritesEntity(
                        FavoritesEntity(
                            recipe.id,
                            recipe.aggregateLikes,
                            recipe.title,
                            recipe.image,
                            recipe.readyInMinutes,
                            recipe.summary,
                            recipe.extendedIngredients,
                            recipe.instructions
                        )
                    )
                }
            }
        }
    }

    override suspend fun getFavoriteRecipes(): Flow<List<FavoriteRecipesModel>> {
        return withContext(Dispatchers.IO) {
            val favoritesEntity = recipesDAO.getFavoritesEntities()
            favoritesEntity.map { recipesList ->
                recipesList.map {
                    FavoriteRecipesModel(
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

    override suspend fun deleteRecipeFavoriteByTitle(title: String) {
        withContext(Dispatchers.IO) {
            recipesDAO.deleteRecipeFavoriteEntityByTitle(title)
        }
    }

    override suspend fun setDarkTheme(isEnable: Boolean) {
        withContext(Dispatchers.IO) {
            sharedPreferenceHelper.setDarkThemeEnable(isEnable)
        }
    }

    override suspend fun isNetworkAvailable(): Boolean {
        return withContext(Dispatchers.IO) {
            internetConnection.isOnline()
        }
    }
}



