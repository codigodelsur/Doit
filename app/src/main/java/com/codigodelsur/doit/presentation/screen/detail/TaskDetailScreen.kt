package com.codigodelsur.doit.presentation.screen.detail

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Checkbox
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.KeyboardArrowDown
import androidx.compose.material.icons.outlined.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.codigodelsur.doit.R
import com.codigodelsur.doit.presentation.component.DoitAlertDialog
import com.codigodelsur.doit.presentation.component.DoitTextButton
import com.codigodelsur.doit.presentation.component.DoitTopBar
import com.codigodelsur.doit.presentation.component.DoitTopBarAction
import com.codigodelsur.doit.presentation.model.PGoal
import com.codigodelsur.doit.presentation.model.PTask
import com.codigodelsur.doit.presentation.screen.home.TaskItem
import com.codigodelsur.doit.presentation.theme.DoitTheme

@Composable
fun TaskScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: TaskDetailViewModel = hiltViewModel(),
) {

    val currentOnNavigateBack by rememberUpdatedState(onNavigateBack)
    LaunchedEffect(viewModel.uiState) {
        if (viewModel.uiState.taskDeleted) {
            currentOnNavigateBack()
        }
    }

    TaskScreenContent(
        uiState = viewModel.uiState,
        modifier = modifier,
        onDeleteTaskConfirm = viewModel::onDeleteTask,
        onDeleteTaskCancel = viewModel::onDismissDialog,
        onUpdateTask = viewModel::onUpdateTask,
        onUpdateGoal = viewModel::onUpdateGoal,
        onShowDialog = viewModel::onShowDialog,
        onGoalsContentChange = viewModel::onGoalsContentChange,
        onNavigateBack = onNavigateBack,
    )
}

@Composable
fun TaskScreenContent(
    uiState: TaskDetailUiState,
    onDeleteTaskConfirm: () -> Unit,
    onDeleteTaskCancel: () -> Unit,
    onUpdateTask: () -> Unit,
    onUpdateGoal: (Long, Boolean) -> Unit,
    onGoalsContentChange: () -> Unit,
    onNavigateBack: () -> Unit,
    onShowDialog: () -> Unit,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
) {
    Scaffold(
        topBar = {
            DoitTopBar(
                title = stringResource(id = R.string.detail_task_top_bar_title),
                onNavigateBack = onNavigateBack,
                actions = {
                    DoitTopBarAction(
                        icon = Icons.Default.Delete,
                        onClick = onShowDialog,
                    )
                },
            )
        },
    ) { contentPadding ->
        if (uiState.task != null) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(contentPadding),
            ) {
                Column(
                    modifier = Modifier
                        .verticalScroll(scrollState)
                        .padding(16.dp)
                        .weight(weight = 1f)
                ) {
                    TaskItem(task = uiState.task)
                    TaskGoalsContent(
                        goals = uiState.task.goals,
                        goalsContentExpanded = uiState.goalsExpanded,
                        onGoalsContentChange = onGoalsContentChange,
                        onUpdateGoal = onUpdateGoal,
                        modifier = Modifier
                            .padding(top = 16.dp),
                    )
                }

                DoitTextButton(
                    text = stringResource(id = R.string.detail_task_button),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    onClick = onUpdateTask,
                )
            }
        } else {
            TaskDetailScreenContentPlaceholder(contentPadding = contentPadding)
        }

        if (uiState.showDeleteTaskDialog) {
            DoitAlertDialog(
                title = stringResource(id = R.string.detail_task_alert_dialog_title),
                description = stringResource(id = R.string.detail_task_alert_dialog_description),
                confirmText = stringResource(id = R.string.detail_task_alert_dialog_confirm_button),
                dismissText = stringResource(id = R.string.detail_task_alert_dialog_cancel_button),
                onConfirm = onDeleteTaskConfirm,
                onCancel = onDeleteTaskCancel,
            )
        }
    }
}

@Composable
fun TaskGoalsContent(
    goals: List<PGoal>,
    goalsContentExpanded: Boolean,
    onGoalsContentChange: () -> Unit,
    onUpdateGoal: (Long, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.animateContentSize(),
        elevation = 0.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(id = R.string.detail_task_goals_subtitle),
                    style = MaterialTheme.typography.h6,
                )
                Icon(
                    imageVector = if (goalsContentExpanded) {
                        Icons.Outlined.KeyboardArrowDown
                    } else {
                        Icons.Outlined.KeyboardArrowUp
                    },
                    modifier = Modifier
                        .padding(end = 12.dp)
                        .clickable { onGoalsContentChange() },
                    contentDescription = stringResource(id = R.string.action_expand_goals),
                )
            }

            Column {
                if (goalsContentExpanded) {
                    goals.forEach { goal ->
                        GoalItem(
                            goal = goal,
                            onUpdateGoal = { onUpdateGoal(goal.id, !goal.isCompleted) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun GoalItem(
    goal: PGoal,
    onUpdateGoal: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = goal.title,
            color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
            style = MaterialTheme.typography.body1,
            modifier = Modifier.weight(1.0f),
        )
        Checkbox(
            checked = goal.isCompleted,
            onCheckedChange = { onUpdateGoal() }
        )
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun TaskDetailScreenPreview() {
    DoitTheme {
        TaskScreenContent(
            uiState = TaskDetailUiState(
                task = PTask.sample,
            ),
            onDeleteTaskConfirm = { },
            onDeleteTaskCancel = { },
            onUpdateTask = { },
            onUpdateGoal = { _, _ -> },
            onNavigateBack = { },
            onShowDialog = { },
            onGoalsContentChange = { },
        )
    }
}
