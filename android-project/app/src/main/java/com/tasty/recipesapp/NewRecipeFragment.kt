package com.tasty.recipesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.tasty.recipesapp.domain.model.RecipeModel

class NewRecipeFragment : Fragment() {

    private lateinit var recipeNameEditText: EditText
    private lateinit var recipeDescriptionEditText: EditText
    private lateinit var recipeIngredientsEditText: EditText
    private lateinit var recipeInstructionsEditText: EditText
    private lateinit var saveRecipeButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView = inflater.inflate(R.layout.fragment_new_recipe, container, false)

        recipeNameEditText = rootView.findViewById(R.id.recipeNameEditText)
        recipeDescriptionEditText = rootView.findViewById(R.id.recipeDescriptionEditText)
        recipeIngredientsEditText = rootView.findViewById(R.id.recipeIngredientsEditText)
        recipeInstructionsEditText = rootView.findViewById(R.id.recipeInstructionsEditText)
        saveRecipeButton = rootView.findViewById(R.id.saveRecipeButton)

        saveRecipeButton.setOnClickListener {
            val recipe = RecipeModel(
                id = 0, // ideiglenesen 0, mert a Room generálja később
                name = recipeNameEditText.text.toString(),
                description = recipeDescriptionEditText.text.toString(),
                thumbnailUrl = "", // ideiglenesen üres
                keywords = "",
                isPublic = true,
                userEmail = "",
                originalVideoUrl = null,
                country = "",
                numServings = 1
            )

            // Visszatérünk a ProfileFragment-re és elküldjük a receptet
            val bundle = bundleOf("newRecipe" to recipe)
            parentFragmentManager.setFragmentResult("addRecipe", bundle)

            findNavController().navigateUp()
        }

        return rootView
    }
}
