package com.example.recipes.di

import android.content.Context
import com.example.recipes.data.ApiServiceRecipes
import com.example.recipes.data.recipes.RecipesRepositoryImpl
import com.example.recipes.data.sharedpref.SharedPreferenceHelper
import com.example.recipes.domain.RecipesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindRecipesRepository(
        recipesRepositoryImpl: RecipesRepositoryImpl
    ): RecipesRepository

    companion object {
        private const val SP_KEY = "SP_KEY"
        private const val BASE_URL = "https://myjsons.com"

        @Provides
        fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferenceHelper {
            return SharedPreferenceHelper(context.getSharedPreferences(SP_KEY, Context.MODE_PRIVATE))
        }

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