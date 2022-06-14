package com.codigodelsur.doit.presentation.screen.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codigodelsur.doit.presentation.model.PTaskStatus
import com.codigodelsur.doit.presentation.model.toHeaderLabelRes
import com.codigodelsur.doit.presentation.theme.DoitTheme

@Composable
fun TaskStatusHeader(
    taskStatus: PTaskStatus,
    modifier: Modifier = Modifier,
) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = modifier.fillMaxWidth(),
    ) {
        Text(
            text = stringResource(id = taskStatus.toHeaderLabelRes()),
            modifier = Modifier.padding(
                horizontal = 8.dp,
                vertical = 4.dp,
            ),
            style = MaterialTheme.typography.subtitle1,
        )
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun TaskStatusHeaderPreview() {
    DoitTheme {
        TaskStatusHeader(
            taskStatus = PTaskStatus.PENDING,
            modifier = Modifier.padding(10.dp),
        )
    }
}
