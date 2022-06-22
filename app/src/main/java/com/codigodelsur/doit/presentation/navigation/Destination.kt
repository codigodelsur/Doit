package com.codigodelsur.doit.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.codigodelsur.doit.R

sealed class Destination(val route: String) {
    object Home : Destination(route = "home")
    object CreateTask : Destination(route = "create-task")
}

enum class BottomNavBarItem(
    val destination: Destination,
    val icon: ImageVector,
    @StringRes val labelRes: Int,
) {
    HOME(
        destination = Destination.Home,
        icon = Icons.Default.Home,
        labelRes = R.string.bottom_nav_bar_item_home,
    ),
    CREATE_TASK(
        destination = Destination.CreateTask,
        icon = Icons.Default.AddCircle,
        labelRes = R.string.bottom_nav_bar_item_create_task,
    )
}
