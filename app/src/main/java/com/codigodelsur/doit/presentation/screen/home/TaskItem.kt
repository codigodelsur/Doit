package com.codigodelsur.doit.presentation.screen.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codigodelsur.doit.presentation.model.PTask
import com.codigodelsur.doit.presentation.theme.DoitTheme
import com.codigodelsur.doit.presentation.util.toFormattedString

@Composable
fun TaskItem(
    task: PTask,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        elevation = 0.dp,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(
                text = stringResource(id = task.status.labelRes).uppercase(),
                color = task.status.color,
                style = MaterialTheme.typography.overline,
            )
            Text(
                text = task.title,
                style = MaterialTheme.typography.h6,
            )
            Text(
                text = task.description,
                color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
                style = MaterialTheme.typography.body1
            )
            Text(
                text = task.date.toFormattedString(),
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(top = 16.dp),
                color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
                style = MaterialTheme.typography.caption,
            )
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun TaskItemPreview() {
    DoitTheme {
        TaskItem(
            task = PTask.sample,
            modifier = Modifier.padding(10.dp),
        )
    }
}
