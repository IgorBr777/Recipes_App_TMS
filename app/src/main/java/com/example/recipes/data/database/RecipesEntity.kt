package com.example.recipes.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("RecipesEntity")
data class RecipesEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("id")
    val id: Int,
    @ColumnInfo("aggregateLikes")
    val aggregateLikes: Int,
    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("image")
    val image: String,
    @ColumnInfo("readyInMinutes")
    val readyInMinutes: Int,
    @ColumnInfo("summary")
    val summary: String,
    @ColumnInfo("extendedIngredients")
    val extendedIngredients: String,
    @ColumnInfo("instructions")
    val instructions: String,
    @ColumnInfo("isFavorite")
    val isFavorite: Boolean? = false

)




