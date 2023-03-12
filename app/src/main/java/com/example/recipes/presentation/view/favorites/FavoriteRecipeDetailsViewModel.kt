package com.example.recipes.presentation.view.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipes.R
import com.example.recipes.domain.RecipesInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoriteRecipeDetailsViewModel @Inject constructor(
    private val recipesInteractor: RecipesInteractor

) : ViewModel() {
    private val _nav = MutableLiveData<Int?>()
    val nav: LiveData<Int?> = _nav

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun deleteRecipeOnDetails(title: String) {
        viewModelScope.launch {
            try {
                recipesInteractor.deleteRecipeFavoriteByTitle(title)
                _nav.value = (R.navigation.main_graph)
            } catch (e: Exception) {
                _error.value = e.message.toString()
            }
        }
    }
}