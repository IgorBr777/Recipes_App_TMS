package com.example.recipes.presentation.view

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    fun getRecipes() {

        viewModelScope.launch {

            try {
                val listRecipes = recipesInteractor.getRecipes()

                _recipes.value = listRecipes


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
            aggregateLikes, extendedIngredients, instructions

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
    val instructions: String
)