package com.example.recipes.di

import com.example.recipes.domain.RecipesInteractor
import com.example.recipes.domain.RecipesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)

class DomainModule {
    @Provides
    fun provideRecipesInteractor(
        recipesRepository: RecipesRepository
    ):RecipesInteractor{
        return RecipesInteractor(recipesRepository)
    }

}