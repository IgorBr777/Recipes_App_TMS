package com.example.recipes.presentation.view

import androidx.lifecycle.ViewModel
import com.example.recipes.domain.RecipesInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val recipesInteractor: RecipesInteractor
):ViewModel() {
}