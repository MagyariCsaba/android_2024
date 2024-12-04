package com.tasty.recipesapp.repository

import android.content.Context
import com.google.gson.Gson
import com.tasty.recipesapp.data.dto.RecipeDTO
import com.tasty.recipesapp.domain.model.RecipeEntity
import com.tasty.recipesapp.domain.model.RecipeModel
import org.json.JSONObject


class RecipeRepository2(context: Context) {
    private val recipeDao = RecipeDatabase.getDatabase(context).recipeDao()
    private val gson = Gson()

    suspend fun getAllRecipes(): List<RecipeModel> {
        val roomRecipes = recipeDao.getAllRecipes()
        return roomRecipes.map { entity ->
            val jsonObject = JSONObject(entity.json)
            val recipe = gson.fromJson(jsonObject.toString(), RecipeDTO::class.java).toModel()
            recipe.internalId = entity.internalId
            recipe
        }
    }

    suspend fun insertRecipe(recipe: RecipeModel) {
        val recipeDto = recipe.toDTO()
        val json = gson.toJson(recipeDto)
        val recipeEntity = RecipeEntity(
            json = json
        )
        recipeDao.insertRecipe(recipeEntity)
    }

    suspend fun getAllRecipeEntities(): List<RecipeEntity> {
        return recipeDao.getAllRecipes()
    }

    suspend fun deleteRecipe(recipe: RecipeEntity) {
        recipeDao.deleteRecipe(recipe)
    }

    private fun RecipeDTO.toModel(): RecipeModel {
        return RecipeModel(
            id = this.recipeID,
            name = this.name,
            description = this.description,
            thumbnailUrl = this.thumbnailUrl,
            keywords = this.keywords,
            isPublic = this.isPublic,
            userEmail = this.userEmail,
            originalVideoUrl = this.originalVideoUrl,
            country = this.country,
            numServings = this.numServings
        )
    }

    private fun RecipeModel.toDTO(): RecipeDTO {
        return RecipeDTO(
            recipeID = this.id,
            name = this.name,
            description = this.description,
            thumbnailUrl = this.thumbnailUrl,
            keywords = this.keywords,
            isPublic = this.isPublic,
            userEmail = this.userEmail,
            originalVideoUrl = this.originalVideoUrl,
            country = this.country,
            numServings = this.numServings
        )
    }

}