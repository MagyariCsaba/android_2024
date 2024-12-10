package com.tasty.recipesapp.data.dto

import com.tasty.recipesapp.domain.model.RecipeModel

data class RecipeDTO(
    val recipeID: Int = 0,
    val name: String ?,
    val description: String?,
    val thumbnailUrl: String?,
    val keywords: String ?,
    val isPublic: Boolean ?,
    val userEmail: String ?,
    val originalVideoUrl: String?,
    val country: String?,
    val numServings: Int?,
   // val components: List<ComponentDTO>,
    //val instructions: List<InstructionDTO>
)
