package com.codigodelsur.doit.presentation.screen.detail

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.codigodelsur.doit.presentation.component.DoitPlaceholderBox
import com.codigodelsur.doit.presentation.screen.home.TaskItemPlaceholder

@Composable
fun TaskDetailScreenContentPlaceholder(
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    val infiniteTransition = rememberInfiniteTransition()
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 0.2f,
        animationSpec = infiniteRepeatable(
            animation = keyframes {
                durationMillis = 1000
                0.1f at 700
            },
            repeatMode = RepeatMode.Reverse
        )
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(contentPadding),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            TaskItemPlaceholder(alpha = alpha)
            TaskGoalsContentPlaceholder(alpha = alpha)
        }
    }
}

@Composable
fun TaskGoalsContentPlaceholder(
    alpha: Float,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = 0.dp
    ) {
        DoitPlaceholderBox(
            height = 100.dp,
            widthFraction = 1f,
            alpha = alpha,
            modifier = Modifier
                .padding(16.dp)
        )
    }
}