package com.tasty.recipesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasty.recipesapp.domain.model.RecipeModel
import com.tasty.recipesapp.repository.RecipeRepository
import kotlinx.coroutines.launch

class RecipeListViewModel(private val repository: RecipeRepository) : ViewModel() {

    // Backing property to hold the recipe data
    private val _recipes = MutableLiveData<List<RecipeModel>>()
    val recipes: LiveData<List<RecipeModel>> get() = _recipes

    // Initialization block to fetch the data from the repository
    init {
        fetchRecipes()
    }

    // Fetch recipes from repository and post the result to LiveData
    fun fetchRecipes() {
        viewModelScope.launch {
            val recipeList = repository.getRecipes()
            _recipes.postValue(recipeList)
        }
    }
}