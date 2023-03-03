package com.example.recipes.presentation.view

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.R
import com.example.recipes.domain.RecipesInteractor
import com.example.recipes.model.RecipesModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipesViewModel @Inject constructor(
    private val recipesInteractor: RecipesInteractor

) : ViewModel() {

    private val _recipes = MutableLiveData<List<RecipesModel>>()
    val recipes: LiveData<List<RecipesModel>> = _recipes

    private val _bundle = MutableLiveData<NavigateWithBundle?>()
    val bundle: LiveData<NavigateWithBundle?> = _bundle

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _findRecipe = MutableLiveData<List<RecipesModel>>()
    val findRecipe: LiveData<List<RecipesModel>> = _findRecipe

    fun getRecipes() {

        viewModelScope.launch {
            try {
                recipesInteractor.getRecipes()
            } catch (e: Exception) {

                _error.value = e.message.toString()

            }

        }

        viewModelScope.launch {

            try {

                val listRecipes = recipesInteractor.showRecipes()
                listRecipes.collect {
                    _recipes.value = it

                }

            } catch (e: Exception) {

                _error.value = e.message.toString()

            }

        }
    }

    fun findRecipe(searchQuery: String) {
        viewModelScope.launch {
            try {
                val foundRecipe = recipesInteractor.findRecipe(searchQuery)
                foundRecipe.collect {
                    _findRecipe.value = it
                }


            } catch (e: Exception) {

                _error.value = e.message.toString()

            }


        }


    }


    fun elementClicked(
        title: String,
        image: String,
        summary: String,
        readyInMinutes: Int,
        aggregateLikes: Int,
        extendedIngredients: String,
        instructions: String

    ) {
        _bundle.value = NavigateWithBundle(
            title,
            image,
            summary,
            readyInMinutes,
            aggregateLikes,
            extendedIngredients,
            instructions,
            R.id.action_recipesFragment_to_recipeDetailsFragment

        )
    }

    fun userNavigated() {

        _bundle.value = null
    }

}

data class NavigateWithBundle(
    val title: String,
    val image: String,
    val summary: String,
    val readyInMinutes: Int,
    val aggregateLikes: Int,
    val extendedIngredients: String,
    val instructions: String,
    val destinationId: Int
)