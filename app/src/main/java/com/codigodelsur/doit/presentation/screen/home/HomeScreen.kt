package com.codigodelsur.doit.presentation.screen.home

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.codigodelsur.doit.R
import com.codigodelsur.doit.presentation.component.DoitPlaceholder
import com.codigodelsur.doit.presentation.model.PTask
import com.codigodelsur.doit.presentation.model.PTaskStatus
import com.codigodelsur.doit.presentation.theme.DoitTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    HomeScreenContent(
        uiState = viewModel.uiState,
        modifier = modifier,
    )
}

@Composable
fun HomeScreenContent(
    uiState: HomeUiState,
    modifier: Modifier = Modifier,
) {
    val tasksGroupedByStatus = uiState.tasksGroupedByStatus

    Scaffold(
        modifier = modifier,
        topBar = {
            HomeToolbar()
        },
    ) { contentPadding ->
        if (tasksGroupedByStatus.isEmpty()) {
            DoitPlaceholder(
                painter = painterResource(id = R.drawable.ic_calendar),
                title = stringResource(id = R.string.home_placeholder_title),
                description = stringResource(id = R.string.home_placeholder_description),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentPadding),
            )
        } else {
            LazyColumn(
                modifier = Modifier.padding(contentPadding),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp),
            ) {
                tasksGroupedByStatus.forEach { (status, tasks) ->
                    item(
                        contentType = PTaskStatus::class.java.simpleName
                    ) {
                        TaskStatusHeader(taskStatus = status)
                    }

                    items(
                        items = tasks,
                        key = { task -> task.id },
                        contentType = { PTask::class.java.simpleName }
                    ) { task ->
                        TaskItem(task = task)
                    }
                }
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
            elevation = 0.dp
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                )
                Text(
                    text = stringResource(id = R.string.app_name),
                    style = MaterialTheme.typography.h6,
                )
            }
        }
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
        HomeScreenContent(
            uiState = HomeUiState(
                tasksGroupedByStatus = PTask.samplesGroupedByStatus,
            ),
        )
    }
}
