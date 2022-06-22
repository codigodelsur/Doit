package com.codigodelsur.doit.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.codigodelsur.doit.presentation.navigation.BottomNavBarItem
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
        Scaffold(
            bottomBar = {
                DoitBottomNavBar(
                    navController = navController,
                    items = BottomNavBarItem.values().toList(),
                )
            }
        ) { contentPadding ->
            NavHost(
                navController = navController,
                startDestination = "home",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding),
            ) {
                composable(route = "home") {
                    HomeScreen()
                }
                composable(route = "create-task") {
                    CreateTaskScreen()
                }
            }
        }
    }
}

@Composable
fun DoitBottomNavBar(
    navController: NavHostController,
    items: List<BottomNavBarItem>,
) {
    BottomNavigation(
        modifier = Modifier.navigationBarsPadding(),
        backgroundColor = MaterialTheme.colors.background,
        contentColor = MaterialTheme.colors.primary,
        elevation = 0.dp,
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = null,
                    )
                },
                label = {
                    Text(text = stringResource(id = item.labelRes))
                },
                selected = currentDestination
                    ?.hierarchy
                    ?.any { it.route == item.destination.route } == true,
                onClick = {
                    navController.navigate(item.destination.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                },
                unselectedContentColor = MaterialTheme.colors.onBackground.copy(
                    alpha = ContentAlpha.disabled
                ),
            )
        }
    }
}
