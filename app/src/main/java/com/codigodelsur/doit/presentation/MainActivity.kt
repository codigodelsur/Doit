package com.codigodelsur.doit.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.codigodelsur.doit.presentation.screen.create.CreateTaskScreen
import com.codigodelsur.doit.presentation.screen.detail.TaskScreen
import com.codigodelsur.doit.presentation.screen.home.HomeScreen
import com.codigodelsur.doit.presentation.theme.DoitTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            DoitTheme {
                DoitApp()
            }
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
                    onViewTaskItem = { task ->
                        val taskId = task.id
                        navController.navigate(route = "detail-task/$taskId")
                    }
                )
            }
            composable(route = "create-task") {
                CreateTaskScreen(
                    onNavigateBack = navController::navigateUp,
                )
            }
            composable(
                route = "detail-task/{id}",
                arguments = listOf(
                    navArgument("id") { type = NavType.LongType }
                )
            ) {
                TaskScreen(
                    onNavigateBack = navController::navigateUp
                )
            }
        }
    }
}
