package com.example.recipes.presentation.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun onFavClicked(title:String,isFavorite:Boolean){
        viewModelScope.launch {
            try {
                recipesInteractor.onFavClicked(title, isFavorite)
            }
            catch (e: Exception) {
                _error.value = e.message.toString()
            }
        }
    }
}





