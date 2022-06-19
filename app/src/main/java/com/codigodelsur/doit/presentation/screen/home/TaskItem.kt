package com.codigodelsur.doit.presentation.screen.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
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
        ConstraintLayout(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            val (status, title, description, date) = createRefs()
            Text(
                text = stringResource(id = task.status.labelRes).uppercase(),
                modifier = Modifier.constrainAs(status) {
                    top.linkTo(anchor = parent.top)
                    start.linkTo(anchor = parent.start)
                },
                color = task.status.color,
                style = MaterialTheme.typography.overline,
            )
            Text(
                text = task.title,
                modifier = Modifier.constrainAs(title) {
                    top.linkTo(
                        anchor = status.bottom,
                        margin = 8.dp,
                    )
                    start.linkTo(anchor = parent.start)
                },
                style = MaterialTheme.typography.h6,
            )
            Text(
                text = task.description,
                modifier = Modifier.constrainAs(description) {
                    top.linkTo(
                        anchor = title.bottom,
                        margin = 8.dp,
                    )
                    start.linkTo(anchor = parent.start)
                },
                color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
                style = MaterialTheme.typography.body1
            )
            Text(
                text = task.date.toFormattedString(),
                modifier = Modifier.constrainAs(date) {
                    top.linkTo(
                        anchor = description.bottom,
                        margin = 24.dp,
                    )
                    end.linkTo(anchor = parent.end)
                },
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
