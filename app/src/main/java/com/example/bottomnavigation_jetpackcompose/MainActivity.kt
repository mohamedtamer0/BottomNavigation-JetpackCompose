package com.example.bottomnavigation_jetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavigation_jetpackcompose.navigation.BottomScreen
import com.example.bottomnavigation_jetpackcompose.navigation.bottomNavigationItems
import com.example.bottomnavigation_jetpackcompose.ui.theme.BottomNavigationJetpackComposeTheme
import com.example.bottomnavigation_jetpackcompose.ui.theme.Purple500

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomNavigationJetpackComposeTheme {
                BottomNavigate()
            }
        }
    }
}

@Composable
fun BottomNavigate() {
    val navController = rememberNavController()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Bottom Navigation With JetPack Compose",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                backgroundColor = Purple500,
                elevation = AppBarDefaults.TopAppBarElevation
            )
        },
        bottomBar = {
            AppBottomNavigation(navController, bottomNavigationItems)
        }
    ) {
        NavHost(
            navController = navController,
            startDestination = BottomScreen.Home.route
        ) {
            composable(BottomScreen.Home.route) {
                BottomHome()
            }
            composable(BottomScreen.Favourite.route) {
                BottomFavourite()
            }
            composable(BottomScreen.Search.route) {
                BottomSearch()
            }
            composable(BottomScreen.User.route) {
                BottomUser()
            }
            composable(BottomScreen.Setting.route) {
                BottomSetting()
            }
        }
    }
}


@Composable
fun AppBottomNavigation(navController: NavController, bottomNavigationItems: List<BottomScreen>) {
    BottomNavigation {
        bottomNavigationItems.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(screen.icon, contentDescription = null)
                },
                label = {
                    Text(text = screen.route)
                },
                selected = false, alwaysShowLabel = false, onClick = {
                    when (screen.route) {
                        "Home" -> navController.navigate(BottomScreen.Home.route)
                        "Favourite" -> navController.navigate(BottomScreen.Favourite.route)
                        "Search" -> navController.navigate(BottomScreen.Search.route)
                        "User" -> navController.navigate(BottomScreen.User.route)
                        "Setting" -> navController.navigate(BottomScreen.Setting.route)
                    }
                }
            )
        }
    }
}

