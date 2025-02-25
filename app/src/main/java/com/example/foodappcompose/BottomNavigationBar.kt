package com.example.foodappcompose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.foodappcompose.model.Bottom

@Composable
fun BottomNavigationBar(navController: NavController) {
    var bottomItem= listOf(
        Bottom("Home",Icons.Default.Home,"Home"),
        Bottom("Favorite",Icons.Default.Favorite,"Favorite"),
        Bottom("Info",Icons.Default.Info,"Info")
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute=navBackStackEntry?.destination?.route

    NavigationBar {
        bottomItem.forEachIndexed { index, bottom ->
            NavigationBarItem(
                selected = currentRoute==bottom.route,
                onClick = {navController.navigate(bottom.route){
                    restoreState=true
                    launchSingleTop=true
                    popUpTo(navController.graph.startDestinationId){
                        saveState
                    }
                } },
                icon = { Icon(imageVector = bottom.icon,contentDescription = null) },
                label = { Text(bottom.label) }
            )
        }
    }
}