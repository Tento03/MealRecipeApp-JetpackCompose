package com.example.foodappcompose.favorite

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriteDao {
    @Insert
    fun addFavorite(favorite: Favorite)

    @Query("SELECT * FROM MealCompose")
    fun getFavorite():Flow<List<Favorite>>

    @Query("SELECT * FROM MealCompose WHERE idMeal=:idMeal")
    fun getFavoriteId(idMeal:String):Flow<Favorite>

    @Query("DELETE FROM MealCompose WHERE idMeal=:idMeal")
    fun deleteFavorite(idMeal: String)
}