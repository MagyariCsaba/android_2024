package com.tasty.recipesapp.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.api.RecipeApiClient
import com.tasty.recipesapp.domain.model.RecipeModel
import com.tasty.recipesapp.repository.RecipeRepository
import kotlinx.coroutines.launch

class RecipeListViewModel(application: Application) : AndroidViewModel(application) {

    private val recipeRepository = RecipeRepository(application.applicationContext, RecipeApiClient())
    private val _recipeList = MutableLiveData<List<RecipeModel>>()
    val recipeList: LiveData<List<RecipeModel>> = _recipeList

    fun fetchRecipeData() {
        viewModelScope.launch {
            _recipeList.value = recipeRepository.getAllRecipes()
        }
    }
}