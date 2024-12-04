package com.tasty.recipesapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tasty.recipesapp.R
import com.tasty.recipesapp.domain.model.RecipeModel

class RecipeAdapter(
    private val recipes: MutableList<RecipeModel>,  // MutableList, hogy módosíthassuk a listát
    private val onItemClick: (RecipeModel) -> Unit,
    private val onDeleteClick: (RecipeModel) -> Unit  // Törlés esemény hozzáadása
) : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    inner class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recipeName: TextView = itemView.findViewById(R.id.recipeNameTextView)
        private val recipeDescription: TextView = itemView.findViewById(R.id.recipeDescriptionTextView)
        private val recipeImage: ImageView = itemView.findViewById(R.id.recipeImageView)
        private val deleteButton: Button = itemView.findViewById(R.id.deleteButton)  // Törlés gomb

        fun bind(recipe: RecipeModel) {
            // Név beállítása
            recipeName.text = recipe.name

            // Leírás beállítása
            recipeDescription.text = recipe.description

            // Kép betöltése Glide-dal
            Glide.with(itemView.context)
                .load(recipe.thumbnailUrl)
                .placeholder(R.drawable.recipe) // Placeholder kép, ha nincs kép
                .into(recipeImage)

            // Kattintási esemény kezelése
            itemView.setOnClickListener { onItemClick(recipe) }

            // Törlés gomb esemény kezelése
            deleteButton.setOnClickListener {
                onDeleteClick(recipe)  // A törlés művelet meghívása
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recipe, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bind(recipes[position])
    }

    override fun getItemCount(): Int = recipes.size

    // Update adapter data
    fun updateData(newRecipes: MutableList<RecipeModel>) {
        recipes.clear()
        recipes.addAll(newRecipes)
        notifyDataSetChanged()
    }

    // Funkció a lista frissítésére a törlés után
    fun removeItem(recipe: RecipeModel) {
        val position = recipes.indexOf(recipe)
        if (position >= 0) {
            recipes.removeAt(position)
            notifyItemRemoved(position)
        }
    }
}
