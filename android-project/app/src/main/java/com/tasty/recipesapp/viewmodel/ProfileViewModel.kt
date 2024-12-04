package com.tasty.recipesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.data.dto.RecipeDTO
import com.tasty.recipesapp.domain.model.RecipeEntity
import com.tasty.recipesapp.domain.model.RecipeModel
import com.tasty.recipesapp.repository.RecipeRepository
import com.tasty.recipesapp.repository.RecipeRepository2
import kotlinx.coroutines.launch

class ProfileViewModel(application: Application) : AndroidViewModel(application){
    private val _recipeList = MutableLiveData<List<RecipeModel>>()
    private val repository: RecipeRepository2 = RecipeRepository2(application.applicationContext)
    val recipeList: LiveData<List<RecipeModel>> = _recipeList

    fun getAllRecipes() {
        viewModelScope.launch {
            _recipeList.value = repository.getAllRecipes()
        }
    }

    fun insertRecipe(recipe: RecipeModel) {
        viewModelScope.launch {
            repository.insertRecipe(recipe)
        }
    }

    fun deleteRecipe(recipe: RecipeModel) {
        val gson = Gson()
        viewModelScope.launch {
            val recipeEntity = RecipeEntity(
                internalId = recipe.internalId,
                json = gson.toJson(recipe)
            )
            repository.deleteRecipe(recipeEntity)
        }
    }
}