package com.codigodelsur.doit.presentation.screen.create

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codigodelsur.doit.R
import com.codigodelsur.doit.presentation.theme.DoitTheme

@Composable
fun GoalEntries(
    goals: List<String>,
    onDelete: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        goals.forEachIndexed { index, title ->
            GoalEntry(
                title = title,
                onDelete = { onDelete(index) }
            )
        }
    }
}

@Composable
fun GoalEntry(
    title: String,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 12.dp, vertical = 4.dp),
            style = MaterialTheme.typography.body1,
        )
        IconButton(onClick = onDelete) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = stringResource(id = R.string.action_remove_goal),
            )
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun GoalEntryPreview() {
    DoitTheme {
        Surface(modifier = Modifier.padding(10.dp)) {
            GoalEntry(
                title = "New goal",
                onDelete = {},
            )
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun GoalEntriesPreview() {
    DoitTheme {
        Surface(modifier = Modifier.padding(10.dp)) {
            GoalEntries(
                goals = List(size = 10) { index -> "Goal #$index" },
                onDelete = {},
            )
        }
    }
}
