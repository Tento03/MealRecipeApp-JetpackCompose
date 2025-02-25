package com.example.foodappcompose.uiux

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.foodappcompose.ui.theme.FoodAppComposeTheme

@Composable
fun InfoView(navController: NavHostController) {
    Column (modifier = Modifier.padding(16.dp)
        .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(80.dp))
        Text("This Application was made by Tento")
    }
}

@Composable
@Preview(showBackground = true)
fun InfoViewPreview(){
    FoodAppComposeTheme {
        val navController= rememberNavController()
        InfoView(navController)
    }
}