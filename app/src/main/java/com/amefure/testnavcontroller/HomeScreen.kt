package com.amefure.testnavcontroller

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.amefure.testnavcontroller.model.Screen

@Composable
fun HomeScreen(
    navController: NavHostController
) {
    Column(
        modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
    ) {
        Text(
            "Home Screen",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(
            modifier = Modifier
                .height(16.dp)
        )

        val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
        val backScreen: String? = savedStateHandle
            ?.getStateFlow<String?>("backScreen", initialValue = null)
            ?.collectAsState()
            ?.value

        if (!backScreen.isNullOrEmpty()) {
            Text(backScreen)
        }


        (1..5).forEach { id ->
            Text(
                text = "Item $id",
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate(Screen.Detail.route(id)) }
                    .padding(8.dp)
            )
        }
        Spacer(
            modifier = Modifier
                .height(16.dp)
        )
        Button(
            onClick = {
                navController.navigate(Screen.Settings.route())
            }
        ) {
            Text("Go to Settings")
        }
    }
}