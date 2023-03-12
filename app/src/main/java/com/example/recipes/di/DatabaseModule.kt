package com.example.recipes.di

import android.content.Context
import com.example.recipes.data.database.dao.RecipesDAO
import com.example.recipes.data.database.dao.RecipesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    fun recipesDatabase(context: Context): RecipesDatabase {
        return RecipesDatabase.getRecipesDatabaseInstance(context)
    }
    @Provides
    fun provideRecipesDao(recipesDatabase: RecipesDatabase): RecipesDAO {
        return recipesDatabase.getRecipesDAO()
    }
}