package com.codigodelsur.doit.presentation.screen.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codigodelsur.doit.domain.interactor.DeleteTaskUseCase
import com.codigodelsur.doit.domain.interactor.ObserveTaskUseCase
import com.codigodelsur.doit.domain.interactor.UpdateTaskUseCase
import com.codigodelsur.doit.presentation.model.toPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class TaskDetailViewModel @Inject constructor(
    private val observeTask: ObserveTaskUseCase,
    private val deleteTask: DeleteTaskUseCase,
    private val updateTask: UpdateTaskUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val taskId = savedStateHandle.get<Long>("id")!!

    var uiState by mutableStateOf(TaskDetailUiState())
        private set

    init {
        viewModelScope.launch {
            observeTask(taskId).collectLatest { task ->
                uiState = uiState.copy(task = task?.toPresentation())
            }
        }
    }

    fun onShowDialog() {
        uiState = uiState.copy(showDeleteTaskDialog = true)
    }

    fun onDismissDialog() {
        uiState = uiState.copy(showDeleteTaskDialog = false)
    }

    fun onUpdateGoal(goalId: Long, isCompleted: Boolean) {
        val updatedGoals = uiState.task!!.goals.map { goal ->
            if (goalId == goal.id) {
                goal.copy(isCompleted = isCompleted)
            } else {
                goal
            }
        }

        uiState = uiState.copy(
            task = uiState.task?.copy(goals = updatedGoals)
        )
    }

    fun onUpdateTask() {
        viewModelScope.launch {
            updateTask(task = uiState.task!!.toDomain())
        }
    }

    fun onDeleteTask() {
        viewModelScope.launch {
            deleteTask(task = uiState.task!!.toDomain())
            uiState = uiState.copy(taskDeleted = true, showDeleteTaskDialog = false)
        }
    }

}
