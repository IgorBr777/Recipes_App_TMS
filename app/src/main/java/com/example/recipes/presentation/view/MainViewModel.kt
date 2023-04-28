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
) : ViewModel() {

    private val _darkThemeEnabled = MutableLiveData<Boolean>()
    val darkThemeEnabled: LiveData<Boolean> = _darkThemeEnabled

    private val _network = MutableLiveData<Boolean>()
    val network: LiveData<Boolean> = _network

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    fun isDarkTheme() {
        viewModelScope.launch {
            try {
                _darkThemeEnabled.value = recipesInteractor.isDarkTheme()
            } catch (e: Exception) {
                _error.value = e.message.toString()
            }
        }
    }

    fun setDarkTheme(isEnable: Boolean) {
        viewModelScope.launch {
            try {
                recipesInteractor.setDarkTheme(isEnable)
                val isEnable = !(darkThemeEnabled.value ?: false)
                _darkThemeEnabled.value = isEnable
            } catch (e: Exception) {
                _error.value = e.message.toString()
            }
        }
    }

    fun isNetworkAvailable() {
        viewModelScope.launch {
            try {
                _network.value = recipesInteractor.isNetworkAvailable()
            } catch (e: Exception) {
                _error.value = e.message.toString()
            }
        }
    }
}







