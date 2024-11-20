package com.tasty.recipesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.tasty.recipesapp.adapter.RecipeAdapter
import com.tasty.recipesapp.domain.model.RecipeModel
import com.tasty.recipesapp.viewmodel.RecipeListViewModel

class ProfileFragment : Fragment() {

    private lateinit var recipeViewModel: RecipeListViewModel
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val rootView = inflater.inflate(R.layout.fragment_profile, container, false)

        // Initialize RecyclerView
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.profileRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Initialize ViewModel
        recipeViewModel = ViewModelProvider(this).get(RecipeListViewModel::class.java)
        recipeViewModel.fetchRecipeData()

        // Initialize Adapter with empty list initially
        recipeAdapter = RecipeAdapter(emptyList()) { recipe -> navigateToRecipeDetail(recipe) }
        recyclerView.adapter = recipeAdapter

        // Observe the recipe list and display 3 random recipes
        recipeViewModel.recipeList.observe(viewLifecycleOwner) { recipes ->
            val randomRecipes = recipes.shuffled().take(3) // Randomly select 3 recipes
            recipeAdapter = RecipeAdapter(randomRecipes) { recipe -> navigateToRecipeDetail(recipe) }
            recyclerView.adapter = recipeAdapter
        }

        // Set up FloatingActionButton for adding a new recipe
        val addRecipeFab = rootView.findViewById<FloatingActionButton>(R.id.addRecipeFab)
        addRecipeFab.setOnClickListener {
            navigateToNewRecipe()
        }

        return rootView
    }

    // Navigation function to navigate to RecipeDetailFragment with selected recipe data
    private fun navigateToRecipeDetail(recipe: RecipeModel) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, RecipeDetailFragment())
            .commit()
    }

    // Navigation function to navigate to NewRecipeFragment
    private fun navigateToNewRecipe() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, NewRecipeFragment())
            .addToBackStack(null) // Add to back stack for proper back navigation
            .commit()
    }
}
