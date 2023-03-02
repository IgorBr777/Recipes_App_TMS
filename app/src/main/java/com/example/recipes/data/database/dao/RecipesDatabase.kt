package com.example.recipes.data.database.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.recipes.data.database.RecipesEntity


@Database(entities = [RecipesEntity::class], version = 1, exportSchema = false)

abstract class RecipesDatabase : RoomDatabase() {

    abstract fun getRecipesDAO(): RecipesDAO


    companion object {
        private const val DATABASE_NAME = "recipes_book"
        private var DATABASE_INSTANCE: RecipesDatabase? = null

        fun getRecipesDatabaseInstance(context: Context): RecipesDatabase {
            return DATABASE_INSTANCE ?: Room
                .databaseBuilder(
                    context.applicationContext,
                    RecipesDatabase::class.java,
                    DATABASE_NAME
                ).build()
                .also { DATABASE_INSTANCE = it }


        }

    }


}