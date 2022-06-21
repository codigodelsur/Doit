package com.codigodelsur.doit.presentation.screen.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.Visibility.Companion.Gone
import androidx.constraintlayout.compose.Visibility.Companion.Visible
import com.codigodelsur.doit.R
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
            modifier = Modifier.fillMaxWidth(),
        ) {
            val (horizontalLine, status, title, description, date, goalsIcon) = createRefs()

            Box(
                modifier = Modifier
                    .constrainAs(horizontalLine) {
                        top.linkTo(anchor = parent.top)
                        bottom.linkTo(anchor = parent.bottom)
                        start.linkTo(anchor = parent.start)

                        height = Dimension.fillToConstraints
                    }
                    .background(task.status.color)
                    .width(4.dp)
            )

            Text(
                text = stringResource(id = task.status.labelRes).uppercase(),
                modifier = Modifier.constrainAs(status) {
                    top.linkTo(
                        anchor = parent.top,
                        margin = 16.dp,
                    )
                    start.linkTo(
                        anchor = horizontalLine.end,
                        margin = 16.dp,
                    )
                    end.linkTo(
                        anchor = goalsIcon.start,
                        margin = 8.dp,
                        goneMargin = 16.dp,
                    )

                    width = Dimension.fillToConstraints
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
                    start.linkTo(anchor = status.start)
                    end.linkTo(anchor = status.end)

                    width = Dimension.fillToConstraints
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
                    start.linkTo(anchor = title.start)
                    end.linkTo(
                        anchor = parent.end,
                        margin = 16.dp,
                    )

                    width = Dimension.fillToConstraints
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
                    end.linkTo(anchor = description.end)
                    bottom.linkTo(
                        anchor = parent.bottom,
                        margin = 16.dp,
                    )
                },
                color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
                style = MaterialTheme.typography.caption,
            )

            Icon(
                painter = painterResource(id = R.drawable.ic_goals),
                modifier = Modifier.constrainAs(goalsIcon) {
                    top.linkTo(anchor = status.top)
                    end.linkTo(
                        anchor = parent.end,
                        margin = 16.dp,
                    )

                    visibility = if (task.goals.isNotEmpty()) Visible else Gone
                },
                tint = MaterialTheme.colors.onSurface.copy(
                    alpha = ContentAlpha.disabled
                ),
                contentDescription = null,
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
