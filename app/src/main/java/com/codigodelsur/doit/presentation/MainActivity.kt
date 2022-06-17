package com.codigodelsur.doit.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codigodelsur.doit.presentation.screen.create.CreateTaskScreen
import com.codigodelsur.doit.presentation.screen.home.HomeScreen
import com.codigodelsur.doit.presentation.theme.DoitTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            DoitApp()
        }
    }
}

@Composable
fun DoitApp() {
    val navController = rememberNavController()
    DoitTheme {
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.fillMaxSize(),
        ) {
            composable(route = "home") {
                HomeScreen(
                    onCreateTask = {
                        navController.navigate(route = "create-task")
                    },
                )
            }
            composable(route = "create-task") {
                CreateTaskScreen(
                    onNavigateBack = navController::navigateUp,
                )
            }
        }
    }
}
