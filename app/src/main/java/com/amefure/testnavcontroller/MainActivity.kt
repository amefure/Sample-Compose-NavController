package com.amefure.testnavcontroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.amefure.testnavcontroller.model.Screen
import com.amefure.testnavcontroller.ui.theme.TestNavControllerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestNavControllerTheme {
                Scaffold { innerPadding ->
                    NavContainer(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
private fun NavContainer(
    modifier: Modifier
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route(),
        modifier = modifier
    ) {
        composable(Screen.Home.route()) {
            HomeScreen(onItemClick = { id ->
                navController.navigate(Screen.Detail.route(id))
            }, onSettingsClick = {
                navController.navigate(Screen.Settings.route())
            })
        }

        composable(
            route = Screen.Detail.route(),
            arguments = listOf(navArgument(Screen.Detail.ARG_ITEM_ID) { type = NavType.IntType })
        ) { backStackEntry ->
            val itemId = backStackEntry.arguments?.getInt(Screen.Detail.ARG_ITEM_ID) ?: 0
            DetailScreen(itemId = itemId, onBack = { navController.popBackStack() })
        }

        composable(Screen.Settings.route()) {
            SettingsScreen(onBack = { navController.popBackStack() })
        }
    }
}
