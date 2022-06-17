package com.codigodelsur.doit.presentation.screen.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codigodelsur.doit.domain.interactor.ObserveTasksUseCase
import com.codigodelsur.doit.domain.model.Task
import com.codigodelsur.doit.presentation.model.PTask
import com.codigodelsur.doit.presentation.model.toPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class HomeViewModel @Inject constructor(
    val observeTasks: ObserveTasksUseCase,
) : ViewModel() {

    var uiState by mutableStateOf(HomeUiState())
        private set

    init {
        viewModelScope.launch {
            observeTasks().collectLatest { tasks ->
                val groupedTasks = tasks
                    .map(Task::toPresentation)
                    .groupBy(PTask::status)

                uiState = uiState.copy(tasksGroupedByStatus = groupedTasks)
            }
        }
    }

}
