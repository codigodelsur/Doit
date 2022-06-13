package com.codigodelsur.doit.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat
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
    DoitTheme {
        HomeScreen()
    }
}
