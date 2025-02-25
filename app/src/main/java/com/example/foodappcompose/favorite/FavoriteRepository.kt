package com.example.foodappcompose.favorite

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FavoriteRepository @Inject constructor(private var favoriteDao: FavoriteDao) {
    suspend fun addFavorite(favorite: Favorite){
        return favoriteDao.addFavorite(favorite)
    }
    suspend fun getFavorite():Flow<List<Favorite>>{
        return favoriteDao.getFavorite()
    }
    fun getFavoriteId(id:String): Flow<Favorite> {
        return favoriteDao.getFavoriteId(id)
    }
    suspend fun deleteFavorite(id: String){
        return favoriteDao.deleteFavorite(id)
    }
}