package com.tasty.recipesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tasty.recipesapp.adapter.RecipeAdapter
import com.tasty.recipesapp.domain.model.RecipeModel
import com.tasty.recipesapp.viewmodel.RecipeListViewModel
import androidx.navigation.fragment.findNavController
import java.io.Serializable

class RecipesFragment : Fragment() {

    private lateinit var recipeViewModel: RecipeListViewModel
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_recipes, container, false)

        // Initialize RecyclerView
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.recipesRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Initialize ViewModel
        recipeViewModel = ViewModelProvider(this).get(RecipeListViewModel::class.java)
        recipeViewModel.fetchRecipeData()

        // Initialize Adapter with empty list initially
        recipeAdapter = RecipeAdapter(emptyList()) { recipe -> navigateToRecipeDetail(recipe) }
        recyclerView.adapter = recipeAdapter

        // Observe the recipe list from ViewModel and update RecyclerView
        recipeViewModel.recipeList.observe(viewLifecycleOwner) { recipes ->
            // Update adapter with new recipe list
            recipeAdapter = RecipeAdapter(recipes) { recipe -> navigateToRecipeDetail(recipe) }
            recyclerView.adapter = recipeAdapter
        }

        return rootView
    }

    // Navigation function to navigate to RecipeDetailFragment with selected recipe data
    private fun navigateToRecipeDetail(recipe: RecipeModel) {
        val fragment = RecipeDetailFragment()
        fragment.arguments = Bundle().apply {
            putInt("recipe", recipe.id)
        }

        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
