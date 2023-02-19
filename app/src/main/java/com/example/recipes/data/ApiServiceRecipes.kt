package com.example.recipes.data

import com.example.recipes.data.model.RecipeDetailsResponse
import com.example.recipes.data.model.RecipesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceRecipes {

    @GET("recipes/complexSearch")
    suspend fun getRecipes(): Response<RecipesResponse>




    @GET("recipes/{id}/information?includeNutrition=false")
    suspend fun getRecipeDetails(): Response<RecipeDetailsResponse>

}