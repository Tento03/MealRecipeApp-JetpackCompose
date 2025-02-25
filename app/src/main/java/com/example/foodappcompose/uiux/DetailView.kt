@file:Suppress("UNREACHABLE_CODE")

package com.example.foodappcompose.uiux

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.foodappcompose.favorite.FavoriteViewModel
import com.example.foodappcompose.meal.MealViewModel
import com.example.foodappcompose.model.Meal
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun DetailView(
    navController: NavHostController,
    mealJson: String?,
    viewModel: MealViewModel = hiltViewModel(),
    favViewModel: FavoriteViewModel = hiltViewModel()
) {
    val detailMeal by viewModel.detailMeal.collectAsState()

    // Parse mealJson untuk mendapatkan objek Meal
    val meal = Gson().fromJson(mealJson, Meal::class.java)
    if (meal != null) {
        viewModel.detailMeal(meal.idMeal)
    }

    favViewModel.getFavoriteId(meal.idMeal)
    val favoriteMeal by favViewModel.getFavoriteId.collectAsState(initial = null)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            if (detailMeal == null) {
                Text(
                    text = "Loading or no data available...",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            } else {
                // Menampilkan gambar dan ikon favorite
                Row(modifier = Modifier.padding(bottom = 16.dp)) {
                    Image(
                        painter = rememberAsyncImagePainter(model = detailMeal?.strMealThumb),
                        contentDescription = null,
                        modifier = Modifier
                            .size(150.dp)
                            .padding(end = 16.dp)
                    )

                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = "Favorite Icon",
                        // Jika meal sudah difavoritkan, ikon menjadi merah
                        tint = if (favoriteMeal != null) Color.Red else Color.Gray,
                        modifier = Modifier
                            .size(48.dp)
                            .clickable {
                                // Ubah status favorit saat diklik
                                if (favoriteMeal == null) {
                                    favViewModel.addFavorite(meal)
                                } else {
                                    favViewModel.deleteFavorite(meal.idMeal)
                                }
                            }
                    )
                }

                Text("Ingredients", fontWeight = FontWeight.Bold)
                Text("${detailMeal?.strIngredient1}, ${detailMeal?.strIngredient2}")

                Text("Instructions", fontWeight = FontWeight.Bold)
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 20.dp)
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        item {
                            detailMeal?.strInstructions?.let { instructions ->
                                Text(
                                    text = instructions,
                                    modifier = Modifier.padding(16.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
