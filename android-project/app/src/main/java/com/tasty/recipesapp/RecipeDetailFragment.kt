package com.tasty.recipesapp

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.tasty.recipesapp.adapter.RecipeAdapter
import com.tasty.recipesapp.databinding.FragmentRecipeDetailBinding
import com.tasty.recipesapp.domain.model.RecipeModel
import com.tasty.recipesapp.viewmodel.RecipeListViewModel

class RecipeDetailFragment : Fragment() {

    private var _binding: FragmentRecipeDetailBinding? = null
    private lateinit var recipeViewModel: RecipeListViewModel
    private val binding get() = _binding!!

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment using View Binding
        _binding = FragmentRecipeDetailBinding.inflate(inflater, container, false)

        recipeViewModel = ViewModelProvider(this).get(RecipeListViewModel::class.java)
        // Retrieve the recipe data passed as arguments
        val recipeId = arguments?.getInt("recipe")

        var recipe: RecipeModel?
        recipeViewModel.recipeList.observe(viewLifecycleOwner) { recipes ->
            recipe = recipes.find { it.id == recipeId }
            recipe?.let { populateRecipeDetails(it) }

        }
        recipeViewModel.fetchRecipeData()


        return binding.root
    }

    private fun populateRecipeDetails(recipe: RecipeModel) {
        binding.apply {
            recipeTitle.text = recipe.name
            recipeDescription.text = recipe.description
            recipeCountry.text = "Country: ${recipe.country}"
            recipeServings.text = "Servings: ${recipe.numServings}"
            recipeKeywords.text = "Keywords: ${recipe.keywords}"

            // Load the thumbnail using Glide
            Glide.with(requireContext())
                .load(recipe.thumbnailUrl)
                .into(recipeThumbnail)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
