package com.codigodelsur.doit

import android.app.Activity
import androidx.activity.ComponentActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.codigodelsur.doit.presentation.model.PTask
import com.codigodelsur.doit.presentation.model.PTaskStatus
import com.codigodelsur.doit.presentation.screen.home.TaskItem
import com.codigodelsur.doit.presentation.theme.DoitTheme
import com.codigodelsur.doit.presentation.util.toFormattedString
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class TaskItemTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    private val activity: Activity by lazy {
        composeTestRule.activity
    }

    private val taskWithGoals = PTask.sample
    private var taskDisplayed by mutableStateOf(taskWithGoals)

    @Before
    fun setup() {
        taskDisplayed = taskWithGoals

        composeTestRule.setContent {
            DoitTheme {
                TaskItem(task = taskDisplayed)
            }
        }
    }

    @Test
    fun pending_isDisplayed_whenTaskIsPending() {
        taskDisplayed = taskWithGoals.copy(status = PTaskStatus.PENDING)

        composeTestRule
            .onNodeWithText(activity.getString(R.string.task_status_pending).uppercase())
            .assertIsDisplayed()
    }

    @Test
    fun inProgress_isDisplayed_whenTaskIsInProgress() {
        taskDisplayed = taskWithGoals.copy(status = PTaskStatus.IN_PROGRESS)

        composeTestRule
            .onNodeWithText(activity.getString(R.string.task_status_in_progress).uppercase())
            .assertIsDisplayed()
    }

    @Test
    fun completed_isDisplayed_whenTaskIsCompleted() {
        taskDisplayed = taskWithGoals.copy(status = PTaskStatus.COMPLETED)

        composeTestRule
            .onNodeWithText(activity.getString(R.string.task_status_completed).uppercase())
            .assertIsDisplayed()
    }

    @Test
    fun taskTitle_isDisplayed() {
        composeTestRule
            .onNodeWithText(taskDisplayed.title)
            .assertIsDisplayed()
    }

    @Test
    fun taskDescription_isDisplayed() {
        composeTestRule
            .onNodeWithText(taskDisplayed.description)
            .assertIsDisplayed()
    }

    @Test
    fun taskDate_isDisplayed() {
        composeTestRule
            .onNodeWithText(taskDisplayed.date.toFormattedString())
            .assertIsDisplayed()
    }

    @Test
    fun goalsIcon_isDisplayed_whenTaskHasGoals() {
        composeTestRule
            .onNodeWithContentDescription(activity.getString(R.string.task_goals_icon_description))
            .assertIsDisplayed()
    }

    @Test
    fun goalsIcon_isNotDisplayed_whenTaskHasNoGoals() {
        taskDisplayed = taskWithGoals.copy(goals = emptyList())

        composeTestRule
            .onNodeWithContentDescription(activity.getString(R.string.task_goals_icon_description))
            .assertDoesNotExist()
    }
}
