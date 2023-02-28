package com.example.recipes.data

import com.example.recipes.data.model.RecipesResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiServiceRecipes {

    @GET("/v/d580806")
    suspend fun getRecipes():Response <RecipesResponse>

}