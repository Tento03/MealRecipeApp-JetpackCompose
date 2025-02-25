package com.example.foodappcompose.uiux

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.foodappcompose.favorite.Favorite
import com.example.foodappcompose.favorite.FavoriteViewModel
import com.example.foodappcompose.meal.MealViewModel
import com.example.foodappcompose.ui.theme.FoodAppComposeTheme
import com.google.gson.Gson

@Composable
fun FavoriteView(navController: NavController, favViewModel: FavoriteViewModel = hiltViewModel(),
                 viewModel: MealViewModel= hiltViewModel()
) {
    var response=favViewModel.getFavorite()
    val getFavorite by favViewModel.getFavorite.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(getFavorite) { favItem ->
                FavoriteCard(
                    label = favItem.strMeal,
                    image = favItem.strMealThumb,
                    onClick = {
                        val mealJson= Gson().toJson(favItem)
                        val encodedMealJson = Uri.encode(mealJson)
                        navController.navigate("Detail/$encodedMealJson")
                        viewModel.detailMeal(favItem.idMeal)
                    }
                )
            }
        }
    }
}


@Composable
fun FavoriteCard(label: String, image: String?,onClick :()->Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp) // Tetapkan tinggi tetap untuk card
            .padding(8.dp), // Padding antar card
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        shape = RoundedCornerShape(16.dp),
        onClick = onClick
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = image),
                contentDescription = null,
                modifier = Modifier
                    .size(150.dp) // Ukuran tetap untuk gambar
                    .aspectRatio(1f) // Rasio aspek 1:1
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Label makanan
            Text(
                text = label,
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp)
            )
        }
    }
}
