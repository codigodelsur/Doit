package com.codigodelsur.doit.presentation.screen.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codigodelsur.doit.R
import com.codigodelsur.doit.presentation.model.PTask
import com.codigodelsur.doit.presentation.theme.DoitTheme
import com.codigodelsur.doit.presentation.util.toFormattedString

@Composable
fun TaskItem(
    task: PTask,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
) {
    Card(
        modifier = modifier
            .run {
                if (onClick != null) clickable { onClick() } else this
            },
        elevation = 0.dp,
    ) {
        Row(modifier = Modifier.height(IntrinsicSize.Min)) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(4.dp)
                    .background(task.status.color)
            )

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Row {
                    Column(
                        modifier = Modifier.weight(1f),
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
                    }
                    if (task.goals.isNotEmpty()) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_goals),
                            modifier = Modifier.padding(start = 8.dp),
                            tint = MaterialTheme.colors.onSurface.copy(
                                alpha = ContentAlpha.disabled
                            ),
                            contentDescription = null,
                        )
                    }
                }
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
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun TaskItemPreview() {
    DoitTheme {
        TaskItem(
            task = PTask.sample,
            modifier = Modifier.padding(10.dp),
            onClick = {}
        )
    }
}
