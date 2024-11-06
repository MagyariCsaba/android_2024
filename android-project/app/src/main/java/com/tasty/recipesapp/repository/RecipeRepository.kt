package com.tasty.recipesapp.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.data.dto.RecipeDTO
import com.tasty.recipesapp.data.dto.toModel
import com.tasty.recipesapp.domain.model.RecipeModel
import java.io.IOException

class RecipeRepository(private val context: Context) {

    // Load recipes from JSON file in assets and map them to RecipeModel
    fun getRecipes(): List<RecipeModel> {
        val json = loadJsonFromAssets("recipes.json") ?: return emptyList()
        val recipeDtoList: List<RecipeDTO> = parseJsonToDto(json)
        return recipeDtoList.map { it.toModel() }
    }

    // Load JSON data from the assets directory
    private fun loadJsonFromAssets(fileName: String): String? {
        return try {
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    // Parse JSON string to a list of RecipeDTO objects
    private fun parseJsonToDto(json: String): List<RecipeDTO> {
        val listType = object : TypeToken<List<RecipeDTO>>() {}.type
        return Gson().fromJson(json, listType)
    }
}
