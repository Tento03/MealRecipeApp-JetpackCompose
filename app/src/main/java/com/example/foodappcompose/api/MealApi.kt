package com.example.foodappcompose.api

import com.example.foodappcompose.model.Meal
import com.example.foodappcompose.model.MealResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MealApi {
    @GET("search.php")
    suspend fun searchMeal(
        @Query("s")s:String
    ):MealResponse

    @GET("lookup.php")
    suspend fun detailMeal(
        @Query("i")i:String
    ):MealResponse
}