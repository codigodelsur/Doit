package com.codigodelsur.doit.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = MountainMeadow,
    onPrimary = DarkGunmetal,
    secondary = MountainMeadow,
    onSecondary = DarkGunmetal,
    background = DarkGunmetal,
    onBackground = Color.White,
    surface = Arsenic,
    onSurface = Color.White,
)

private val LightColorPalette = lightColors(
    primary = MountainMeadow,
    onPrimary = Color.White,
    secondary = MountainMeadow,
    onSecondary = Color.White,
    background = Color.White,
    onBackground = Color.Black,
    surface = Cultured,
    onSurface = Color.Black,
)

@Composable
fun DoitTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}