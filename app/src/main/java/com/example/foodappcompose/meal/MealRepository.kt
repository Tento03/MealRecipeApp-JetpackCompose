package com.example.foodappcompose.meal

import com.example.foodappcompose.api.MealApi
import com.example.foodappcompose.model.MealResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MealRepository @Inject constructor(private var mealApi: MealApi) {
    suspend fun searchMeal(s:String):MealResponse{
        return mealApi.searchMeal(s)
    }
    suspend fun detailMeal(i:String):MealResponse{
        return mealApi.detailMeal(i)
    }
}