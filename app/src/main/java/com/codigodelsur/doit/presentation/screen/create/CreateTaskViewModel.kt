package com.codigodelsur.doit.presentation.screen.create

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codigodelsur.doit.domain.interactor.CreateTaskUseCase
import com.codigodelsur.doit.presentation.util.toDateOrNull
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class CreateTaskViewModel @Inject constructor(
    private val createTask: CreateTaskUseCase,
) : ViewModel() {

    var uiState by mutableStateOf(CreateTaskUiState())
        private set

    fun onTitleChange(title: String) {
        uiState = uiState.copy(title = title)
    }

    fun onDescriptionChange(description: String) {
        uiState = uiState.copy(description = description)
    }

    fun onDateChange(formattedDate: String) {
        uiState = uiState.copy(date = formattedDate)
    }

    fun onGoalChange(goal: String) {
        uiState = uiState.copy(goal = goal)
    }

    fun onSubmitGoal() {
        val updatedGoals = uiState.goals + uiState.goal
        uiState = uiState.copy(
            goal = "",
            goals = updatedGoals,
        )
    }

    fun onDeleteGoal(index: Int) {
        val updatedGoals = uiState.goals.filterIndexed { goalIndex, _ -> goalIndex != index }
        uiState = uiState.copy(
            goals = updatedGoals,
        )
    }

    fun onCreateTask() {
        viewModelScope.launch {
            with(uiState) {
                createTask(
                    title = title,
                    description = description,
                    date = date.toDateOrNull() ?: Date(),
                    goals = goals,
                )
                uiState = uiState.copy(taskCreated = true)
            }
        }
    }
}
