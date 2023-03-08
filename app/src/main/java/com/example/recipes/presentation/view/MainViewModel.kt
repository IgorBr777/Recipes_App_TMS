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
class MainViewModel @Inject constructor(
    private val recipesInteractor: RecipesInteractor
):ViewModel() {

    private val _darkThemeEnabled = MutableLiveData<Boolean>()
    val darkThemeEnabled: LiveData<Boolean> = _darkThemeEnabled


    fun setDarkTheme(isEnable: Boolean) {
        viewModelScope.launch {
            recipesInteractor.setDarkTheme(isEnable)
            val isDarkTheme = !(darkThemeEnabled.value ?: false)
            _darkThemeEnabled.value = isDarkTheme

        }

    }


}







