package com.example.recipes.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.Companion.IGNORE
import androidx.room.Query
import com.example.recipes.data.database.FavoritesEntity
import com.example.recipes.data.database.RecipesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDAO {

    @Insert
    fun insertRecipesEntity(recipesEntity: RecipesEntity)

    @Query("SELECT * FROM RecipesEntity")
    fun getRecipesEntities(): Flow<List<RecipesEntity>>

    @Query("SELECT(SELECT COUNT(*) FROM RecipesEntity) !=0")
    fun doesRecipesEntityExist():Flow <Boolean>

    @Query("SELECT * FROM RecipesEntity WHERE title =:searchQuery")
    fun findRecipeEntityByTitle(searchQuery: String):Flow<List<RecipesEntity>>

    @Insert(onConflict = IGNORE)
    fun insertFavoritesEntity(favoritesEntity: FavoritesEntity)

    @Query("UPDATE RecipesEntity SET isFavorite=:isFavorite WHERE title =:title")
    fun  addFavorite(title: String, isFavorite:Boolean)

    @Query("SELECT * FROM FavoritesEntity")
    fun getFavoritesEntities():Flow <List<FavoritesEntity>>

    @Query("DELETE FROM FavoritesEntity WHERE title =:title")
    fun deleteRecipeFavoriteEntityByTitle(title: String)



}