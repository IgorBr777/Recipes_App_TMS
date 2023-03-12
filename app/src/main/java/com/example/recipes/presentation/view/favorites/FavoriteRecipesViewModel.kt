package com.example.recipes.presentation.view.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.R
import com.example.recipes.domain.RecipesInteractor
import com.example.recipes.model.FavoriteRecipesModel
import com.example.recipes.presentation.view.NavigateWithBundle
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoriteRecipesViewModel @Inject constructor(
    private val recipesInteractor: RecipesInteractor
) :
    ViewModel() {

    private val _favorites = MutableLiveData<List<FavoriteRecipesModel>>()
    val favorites: LiveData<List<FavoriteRecipesModel>> = _favorites

    private val _favbundle = MutableLiveData<NavigateWithBundle?>()
    val favbundle: LiveData<NavigateWithBundle?> = _favbundle

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun getFavoriteRecipes() {
        viewModelScope.launch {
            try {
                val favoriteRecipes = recipesInteractor.getFavoriteRecipes()
                favoriteRecipes.collect{
                    _favorites.value = it
                }
            } catch (e: Exception) {
                _error.value = e.message.toString()
            }
        }
    }

    fun favoriteElementClicked(
        title: String,
        image: String,
        summary: String,
        readyInMinutes: Int,
        aggregateLikes: Int,
        extendedIngredients: String,
        instructions: String

    ) {
        _favbundle.value = NavigateWithBundle(
            title,
            image,
            summary,
            readyInMinutes,
            aggregateLikes,
            extendedIngredients,
            instructions,
            R.id.action_favoriteRecipesFragment_to_favoriteRecipeDetailsFragment
        )
    }

    fun userNavigated() {
        _favbundle.value = null
    }

    fun deleteRecipe(title: String){
        viewModelScope.launch {
            try {
                recipesInteractor.deleteRecipeFavoriteByTitle(title)
            }
            catch (e: Exception) {
                _error.value = e.message.toString()
            }
        }
    }
}


