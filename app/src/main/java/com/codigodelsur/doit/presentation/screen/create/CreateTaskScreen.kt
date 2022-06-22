package com.codigodelsur.doit.presentation.screen.create

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.codigodelsur.doit.R
import com.codigodelsur.doit.presentation.component.DoitTextButton
import com.codigodelsur.doit.presentation.component.DoitTopBar
import com.codigodelsur.doit.presentation.theme.DoitTheme
import com.codigodelsur.doit.presentation.util.DateVisualTransformation

@Composable
fun CreateTaskScreen(
    modifier: Modifier = Modifier,
    viewModel: CreateTaskViewModel = hiltViewModel(),
) {
    CreateTaskScreenContent(
        uiState = viewModel.uiState,
        onTitleChange = viewModel::onTitleChange,
        onDescriptionChange = viewModel::onDescriptionChange,
        onDateChange = viewModel::onDateChange,
        onGoalChange = viewModel::onGoalChange,
        onSubmitGoal = viewModel::onSubmitGoal,
        onDeleteGoal = viewModel::onDeleteGoal,
        onCreateTask = viewModel::onCreateTask,
        modifier = modifier,
    )
}

@Composable
fun CreateTaskScreenContent(
    uiState: CreateTaskUiState,
    onTitleChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onDateChange: (String) -> Unit,
    onGoalChange: (String) -> Unit,
    onSubmitGoal: () -> Unit,
    onDeleteGoal: (Int) -> Unit,
    onCreateTask: () -> Unit,
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            DoitTopBar(
                title = stringResource(id = R.string.create_task_top_bar_title),
            )
        }
    ) { contentPadding ->
        Surface(
            modifier = Modifier
                .padding(contentPadding)
                .navigationBarsPadding(),
            color = MaterialTheme.colors.background,
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                Column(
                    modifier = Modifier
                        .verticalScroll(scrollState)
                        .weight(weight = 1f)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                ) {
                    FormField(
                        label = stringResource(id = R.string.create_task_title_label),
                        value = uiState.title,
                        onValueChange = onTitleChange,
                        hint = stringResource(id = R.string.create_task_title_hint),
                        singleLine = true,
                    )
                    FormField(
                        label = stringResource(id = R.string.create_task_description_label),
                        value = uiState.description,
                        onValueChange = onDescriptionChange,
                        hint = stringResource(id = R.string.create_task_description_hint),
                        minHeight = 130.dp,
                    )
                    FormField(
                        label = stringResource(id = R.string.create_task_date_label),
                        value = uiState.date,
                        onValueChange = { formattedDate ->
                            if (formattedDate.count() < 10) {
                                onDateChange(formattedDate)
                            }
                        },
                        hint = stringResource(id = R.string.create_task_date_hint),
                        trailingIcon = Icons.Default.DateRange,
                        visualTransformation = DateVisualTransformation(),
                        keyboardType = KeyboardType.Number,
                        singleLine = true,
                    )
                    FormField(
                        label = stringResource(id = R.string.create_task_goals_label),
                        value = uiState.goal,
                        onValueChange = onGoalChange,
                        hint = stringResource(id = R.string.create_task_goals_hint),
                        keyboardActions = KeyboardActions {
                            onSubmitGoal()
                        },
                        singleLine = true
                    )
                    if (uiState.goals.isNotEmpty()) {
                        GoalEntries(
                            goals = uiState.goals,
                            onDelete = onDeleteGoal,
                        )
                    }
                }

                DoitTextButton(
                    text = stringResource(id = R.string.create_task_button),
                    onClick = onCreateTask,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                )
            }
        }
    }
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun CreateTaskContentPreview() {
    DoitTheme {
        CreateTaskScreenContent(
            uiState = CreateTaskUiState(),
            onTitleChange = {},
            onDescriptionChange = {},
            onDateChange = {},
            onGoalChange = {},
            onSubmitGoal = {},
            onDeleteGoal = {},
            onCreateTask = {},
        )
    }
}
