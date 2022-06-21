package com.codigodelsur.doit.presentation.component

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codigodelsur.doit.presentation.theme.DoitTheme

@Composable
fun DoitTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        elevation = ButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            disabledElevation = 0.dp,
            hoveredElevation = 0.dp,
            focusedElevation = 0.dp,
        ),
        contentPadding = PaddingValues(16.dp),
    ) {
        Text(text = text)
    }
}

@Composable
fun DoitOutlinedTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        border = BorderStroke(2.dp, MaterialTheme.colors.primary),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.background,
            contentColor = MaterialTheme.colors.primary,
        ),
        contentPadding = PaddingValues(16.dp),
    ) {
        Text(text = text)
    }
}

@Composable
fun DoitFloatingActionButton(
    icon: ImageVector,
    contentDescription: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            hoveredElevation = 0.dp,
            focusedElevation = 0.dp,
        ),
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
        )
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DoitTextButtonPreview() {
    DoitTheme {
        DoitTextButton(
            text = "Text",
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
        )
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DoitOutlinedTextButtonPreview() {
    DoitTheme {
        DoitOutlinedTextButton(
            text = "Text",
            onClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
        )
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DoitFloatingActionButtonPreview() {
    DoitTheme {
        DoitFloatingActionButton(
            icon = Icons.Default.Add,
            contentDescription = null,
            onClick = {},
            modifier = Modifier.padding(10.dp),
        )
    }
}
