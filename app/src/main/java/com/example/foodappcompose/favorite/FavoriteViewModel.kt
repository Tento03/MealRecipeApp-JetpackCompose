package com.example.foodappcompose.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodappcompose.model.Meal
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private var repository: FavoriteRepository):ViewModel() {
    private val _getFavorite=MutableStateFlow<List<Favorite>>(emptyList())
    val getFavorite:MutableStateFlow<List<Favorite>> = _getFavorite

    private val _getFavoriteId=MutableStateFlow<Favorite?>(null)
    val getFavoriteId:MutableStateFlow<Favorite?> = _getFavoriteId

    fun addFavorite(meal: Meal){
        viewModelScope.launch(Dispatchers.IO) {
            var favorite=Favorite(
                idMeal = meal.idMeal,
                strMeal = meal.strMeal,
                strDrinkAlternate = meal.strDrinkAlternate ?: "",
                strCategory = meal.strCategory,
                strArea = meal.strArea,
                strInstructions = meal.strInstructions ?: "",
                strMealThumb = meal.strMealThumb ?: "",
                strTags = meal.strTags ?: "",
                strYoutube = meal.strYoutube ?: "",
                strIngredient1 = meal.strIngredient1 ?: "",
                strIngredient2 = meal.strIngredient2 ?: "",
                strIngredient3 = meal.strIngredient3 ?: "",
                strIngredient4 = meal.strIngredient4 ?: "",
                strIngredient5 = meal.strIngredient5 ?: "",
                strIngredient6 = meal.strIngredient6 ?: "",
                strIngredient7 = meal.strIngredient7 ?: "",
                strIngredient8 = meal.strIngredient8 ?: "",
                strIngredient9 = meal.strIngredient9 ?: "",
                strIngredient10 = meal.strIngredient10 ?: "",
                strIngredient11 = meal.strIngredient11 ?: "",
                strIngredient12 = meal.strIngredient12 ?: "",
                strIngredient13 = meal.strIngredient13 ?: "",
                strIngredient14 = meal.strIngredient14 ?: "",
                strIngredient15 = meal.strIngredient15 ?: "",
                strIngredient16 = meal.strIngredient16 ?: "",
                strIngredient17 = meal.strIngredient17 ?: "",
                strIngredient18 = meal.strIngredient18 ?: "",
                strIngredient19 = meal.strIngredient19 ?: "",
                strIngredient20 = meal.strIngredient20 ?: "",
                strMeasure1 = meal.strMeasure1 ?: "",
                strMeasure2 = meal.strMeasure2 ?: "",
                strMeasure3 = meal.strMeasure3 ?: "",
                strMeasure4 = meal.strMeasure4 ?: "",
                strMeasure5 = meal.strMeasure5 ?: "",
                strMeasure6 = meal.strMeasure6 ?: "",
                strMeasure7 = meal.strMeasure7 ?: "",
                strMeasure8 = meal.strMeasure8 ?: "",
                strMeasure9 = meal.strMeasure9 ?: "",
                strMeasure10 = meal.strMeasure10 ?: "",
                strMeasure11 = meal.strMeasure11 ?: "",
                strMeasure12 = meal.strMeasure12 ?: "",
                strMeasure13 = meal.strMeasure13 ?: "",
                strMeasure14 = meal.strMeasure14 ?: "",
                strMeasure15 = meal.strMeasure15 ?: "",
                strMeasure16 = meal.strMeasure16 ?: "",
                strMeasure17 = meal.strMeasure17 ?: "",
                strMeasure18 = meal.strMeasure18 ?: "",
                strMeasure19 = meal.strMeasure19 ?: "",
                strMeasure20 = meal.strMeasure20 ?: "",
                strSource = meal.strSource ?: "",
                strImageSource = meal.strImageSource ?: "",
                strCreativeCommonsConfirmed = meal.strCreativeCommonsConfirmed ?: "",
                dateModified = meal.dateModified ?: ""
            )
            repository.addFavorite(favorite)
        }
    }

    fun getFavorite(){
        try {
            viewModelScope.launch {
                repository.getFavorite().collect{
                    _getFavorite.value=it
                }
            }
        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }

    fun getFavoriteId(id:String) {
        try {
            viewModelScope.launch {
                repository.getFavoriteId(id).collect{
                    getFavoriteId.value=it
                }
            }
        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }

    fun deleteFavorite(id: String){
        try {
            viewModelScope.launch(Dispatchers.IO) {
                repository.deleteFavorite(id)
            }
        }
        catch (e:Exception){
            e.printStackTrace()
        }
    }

    fun isFavorite(mealId: String): StateFlow<Boolean> {
        val isFavoriteFlow = MutableStateFlow(false)
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFavoriteId(mealId).collect { favorite ->
                isFavoriteFlow.value = favorite != null
            }
        }
        return isFavoriteFlow
    }
}