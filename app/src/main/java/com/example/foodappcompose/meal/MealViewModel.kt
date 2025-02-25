package com.example.foodappcompose.meal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodappcompose.model.Meal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MealViewModel @Inject constructor(private var mealRepository: MealRepository):ViewModel() {
    private val _searchMeal=MutableStateFlow<List<Meal>>(emptyList())
    val searchMeal:MutableStateFlow<List<Meal>> = _searchMeal

    private val _detailMeal=MutableStateFlow<Meal?>(null)
    val detailMeal:MutableStateFlow<Meal?> = _detailMeal

    fun searchMeal(s:String){
        try {
            viewModelScope.launch {
                var response=mealRepository.searchMeal(s).meals
                _searchMeal.value=response
            }
        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }

    fun detailMeal(i:String){
        try {
            viewModelScope.launch {
                val response=mealRepository.detailMeal(i)
                _detailMeal.value=response.meals.firstOrNull()
            }
        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }
}