package com.tasty.recipesapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.util.Log
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.tasty.recipesapp.viewmodel.RecipeListViewModel
import com.tasty.recipesapp.viewmodel.RecipeListViewModelFactory

class RecipesFragment : Fragment(R.layout.fragment_recipes) {


    private val viewModel: RecipeListViewModel by viewModels {
        RecipeListViewModelFactory(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.fetchRecipes()


        viewModel.recipes.observe(viewLifecycleOwner, Observer { recipes ->
            // Log each recipe's details to Logcat
            for (recipe in recipes) {
                //Log.d("RecipeData", "Recipe ID: ${recipe.recipeID}")
                Log.d("RecipeData", "Recipe Name: ${recipe.name}")
                Log.d("RecipeData", "Recipe Description: ${recipe.description}")
                // Add more properties as needed
            }
        })
    }
}