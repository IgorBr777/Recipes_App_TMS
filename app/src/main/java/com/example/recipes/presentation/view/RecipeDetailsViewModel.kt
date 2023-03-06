package com.example.recipes.presentation.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.domain.RecipesInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel

class RecipeDetailsViewModel  @Inject constructor(
    private  val  recipesInteractor: RecipesInteractor

): ViewModel() {

    fun onFavClicked(title:String,isFavorite:Boolean){
        viewModelScope.launch {
            recipesInteractor.onFavClicked(title, isFavorite)

        }

    }

}





