package com.example.recipes.di

import com.example.recipes.data.ApiServiceRecipes
import com.example.recipes.data.recipes.RecipesRepositoryImpl
import com.example.recipes.domain.RecipesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindRecipesRepository(
        recipesRepositoryImpl: RecipesRepositoryImpl
    ): RecipesRepository

    companion object {

        private const val BASE_URL = "https://spoonacular.com/food-api/"
        private const val API_KEY = "d3bd6a4493e9450793ccdf41037ccc36"


        @Provides
        fun provideApiService(retrofit: Retrofit): ApiServiceRecipes {
            return retrofit.create(ApiServiceRecipes::class.java)
        }


        @Provides
        fun provideRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

    }


}