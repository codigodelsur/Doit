package com.codigodelsur.doit.presentation.screen.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.codigodelsur.doit.R
import com.codigodelsur.doit.presentation.model.PTask
import com.codigodelsur.doit.presentation.theme.DoitTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    HomeScreenContent(
        tasks = PTask.samples,
        modifier = modifier,
    )
}

@Composable
fun HomeScreenContent(
    tasks: List<PTask>,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            HomeToolbar()
        },
    ) { contentPadding ->
        LazyColumn(
            modifier = Modifier.padding(contentPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(16.dp),
        ) {
            items(
                items = tasks,
                key = { task -> task.id },
            ) { task ->
                TaskItem(task = task)
            }
        }
    }
}

@Composable
fun HomeToolbar(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Spacer(
            modifier = Modifier
                .windowInsetsTopHeight(WindowInsets.statusBars)
                .fillMaxWidth()
                .background(MaterialTheme.colors.background),
        )

        TopAppBar(
            backgroundColor = MaterialTheme.colors.background,
            elevation = 0.dp,
            title = {
                Text(
                    text = stringResource(id = R.string.app_name),
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                )
            }
        )
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HomeToolbarPreview() {
    DoitTheme {
        HomeToolbar(modifier = Modifier.padding(10.dp))
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenContentPreview() {
    DoitTheme {
        HomeScreenContent(tasks = PTask.samples)
    }
}
