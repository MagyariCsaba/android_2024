package com.tasty.recipesapp.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.tasty.recipesapp.data.dto.RecipeDTO
import com.tasty.recipesapp.data.dto.toModel
import com.tasty.recipesapp.domain.model.RecipeModel
import java.io.IOException

class RecipeRepository(private val context: Context) {


    fun getRecipes(): List<RecipeModel> {
        val json = loadJsonFromAssets("more_recipes.json") ?: return emptyList()
        val recipeDtoList: List<RecipeDTO> = parseJsonToDto(json)
        return recipeDtoList.map { it.toModel() }
    }


    private fun loadJsonFromAssets(fileName: String): String? {
        return try {
            context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }


    private fun parseJsonToDto(json: String): List<RecipeDTO> {
        val listType = object : TypeToken<List<RecipeDTO>>() {}.type
        return Gson().fromJson(json, listType)
    }
}
