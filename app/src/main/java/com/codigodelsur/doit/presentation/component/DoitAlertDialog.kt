package com.codigodelsur.doit.presentation.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codigodelsur.doit.presentation.theme.DoitTheme

@Composable
fun DoitAlertDialog(
    title: String,
    description: String,
    confirmText: String,
    dismissText: String,
    onConfirm: () -> Unit,
    onCancel: () -> Unit
) {
    AlertDialog(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.h6
            )
        },
        text = {
            Text(
                text = description,
                style = MaterialTheme.typography.body1
            )
        },
        buttons = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                DoitOutlinedTextButton(
                    text = dismissText,
                    modifier = Modifier.weight(1.0f),
                    onClick = onCancel
                )
                DoitTextButton(
                    text = confirmText,
                    modifier = Modifier.weight(1.0f),
                    onClick = onConfirm
                )
            }
        },
        onDismissRequest = onCancel
    )
}

@Preview
@Composable
fun DoitAlertDialogPreview() {
    DoitTheme {
        DoitAlertDialog(
            title = "Alert",
            description = "Lorem input hh asdads asdasd",
            confirmText = "Confirm",
            dismissText = "Cancel",
            onConfirm = { },
            onCancel = { }
        )
    }
}