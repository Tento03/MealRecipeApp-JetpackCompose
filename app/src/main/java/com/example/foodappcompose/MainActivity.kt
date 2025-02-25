package com.example.foodappcompose

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.foodappcompose.model.Meal
import com.example.foodappcompose.ui.theme.FoodAppComposeTheme
import com.example.foodappcompose.uiux.DetailView
import com.example.foodappcompose.uiux.FavoriteView
import com.example.foodappcompose.uiux.InfoView
import com.example.foodappcompose.uiux.MealView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    companion object{
        const val key="1"
        val mealer=Meal(
            idMeal = "",
            strMeal = "",
            strDrinkAlternate = "",
            strCategory = "",
            strArea = "",
            strInstructions = "",
            strMealThumb = "",
            strTags = "",
            strYoutube = "",
            strIngredient1 = "",
            strIngredient2 = "",
            strIngredient3 = "",
            strIngredient4 = "",
            strIngredient5 = "",
            strIngredient6 = "",
            strIngredient7 = "",
            strIngredient8 = "",
            strIngredient9 = "",
            strIngredient10 = "",
            strIngredient11 = "",
            strIngredient12 = "",
            strIngredient13 = "",
            strIngredient14 = "",
            strIngredient15 = "",
            strIngredient16 = "",
            strIngredient17 = "",
            strIngredient18 = "",
            strIngredient19 = "",
            strIngredient20 = "",
            strMeasure1 = "",
            strMeasure2 = "",
            strMeasure3 = "",
            strMeasure4 = "",
            strMeasure5 = "",
            strMeasure6 = "",
            strMeasure7 = "",
            strMeasure8 = "",
            strMeasure9 = "",
            strMeasure10 = "",
            strMeasure11 = "",
            strMeasure12 = "",
            strMeasure13 = "",
            strMeasure14 = "",
            strMeasure15 = "",
            strMeasure16 = "",
            strMeasure17 = "",
            strMeasure18 = "",
            strMeasure19 = "",
            strMeasure20 = "",
            strSource = "",
            strImageSource = "",
            strCreativeCommonsConfirmed = "",
            dateModified = ""
        ).idMeal
    }
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodAppComposeTheme {
                val navController= rememberNavController()
                Scaffold (bottomBar = { BottomNavigationBar(navController) }) { innerPadding->
                    NavHost(navController=navController, startDestination = "Home", modifier = Modifier.padding(innerPadding)){
                        composable(route = "Home"){
                            MealView(navController)
                        }
                        composable(route = "Detail/{mealJson}",
                            arguments = listOf(navArgument("mealJson"){
                                type=NavType.StringType
                            })
                        ){backStackEntry ->
                            val mealJson = backStackEntry.arguments?.getString("mealJson")
                            DetailView(navController,mealJson)
                        }
                        composable(route = "Favorite"){
                            FavoriteView(navController)
                        }
                        composable(route = "Info"){
                            InfoView(navController)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FoodAppComposeTheme {
        Greeting("Android")
    }
}