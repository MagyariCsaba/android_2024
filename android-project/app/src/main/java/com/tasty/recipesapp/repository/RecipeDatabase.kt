package com.tasty.recipesapp.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tasty.recipesapp.domain.model.RecipeDao
import com.tasty.recipesapp.domain.model.RecipeEntity

@Database(entities = [RecipeEntity.RecipeEntity::class], version = 1, exportSchema =
false)
abstract class RecipeDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
    companion object {
        // Create a singleton instance of the database
        @Volatile
        private var INSTANCE: RecipeDatabase? = null
        fun getDatabase(context: Context): RecipeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecipeDatabase::class.java,
                    "recipe_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
