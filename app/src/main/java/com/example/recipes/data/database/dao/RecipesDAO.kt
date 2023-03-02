package com.example.recipes.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.recipes.data.database.RecipesEntity

@Dao
interface RecipesDAO {

    @Insert
    fun insertRecipesEntity(recipesEntity: RecipesEntity)

    @Query("SELECT * FROM RecipesEntity")
    fun getRecipesEntities(): List<RecipesEntity>

    @Query("SELECT(SELECT COUNT(*) FROM RecipesEntity) !=0")
    fun doesRecipesEntityExist(): Boolean

    @Query("SELECT * FROM RecipesEntity WHERE title =:searchText")

    fun findRecipeEntityByTitle(searchText: String):List<RecipesEntity>

}