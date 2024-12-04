package com.tasty.recipesapp

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.tasty.recipesapp.adapter.RecipeAdapter
import com.tasty.recipesapp.domain.model.RecipeEntity
import com.tasty.recipesapp.domain.model.RecipeModel
import com.tasty.recipesapp.viewmodel.ProfileViewModel

class ProfileFragment : Fragment() {

    private lateinit var profileViewModel: ProfileViewModel
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
        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        profileViewModel.getAllRecipes()

        // Initialize Adapter with empty list initially
        recipeAdapter = RecipeAdapter(
            mutableListOf(), // MutableList to allow dynamic updates
            onItemClick = { recipe -> navigateToRecipeDetail(recipe) },
            onDeleteClick = { recipe -> confirmDelete(recipe) }
        )
        recyclerView.adapter = recipeAdapter

        // Observe the recipe list and update the adapter
        profileViewModel.recipeList.observe(viewLifecycleOwner) { recipes ->
            recipeAdapter.updateData(recipes.toMutableList())
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

    // Handle recipe deletion
    private fun deleteRecipe(recipe: RecipeModel) {
        profileViewModel.deleteRecipe(recipe)  // Remove from ViewModel
        recipeAdapter.removeItem(recipe)  // Update adapter
    }

    private fun confirmDelete(recipe: RecipeModel) {
        // AlertDialog létrehozása
        AlertDialog.Builder(requireContext())
            .setTitle("Törlés megerősítése")
            .setMessage("Biztosan törölni szeretnéd a(z) ${recipe.name} receptet?")
            .setPositiveButton("Igen") { _, _ ->
                // Ha a felhasználó megerősíti, töröljük a receptet
                profileViewModel.deleteRecipe(recipe)  // ViewModel-ből törlés
                recipeAdapter.removeItem(recipe)  // Adapter frissítése
            }
            .setNegativeButton("Nem", null) // Ha a felhasználó mégsem akarja törölni
            .show()
    }

}
