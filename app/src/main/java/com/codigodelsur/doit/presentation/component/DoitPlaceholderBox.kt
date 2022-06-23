package com.codigodelsur.doit.presentation.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

@Composable
fun DoitPlaceholderBox(
    height: Dp,
    widthFraction: Float,
    alpha: Float,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .height(height = height)
            .background(
                color = LocalContentColor.current.copy(alpha = alpha),
                shape = MaterialTheme.shapes.small,
            )
            .fillMaxWidth(fraction = widthFraction),
    )
}
