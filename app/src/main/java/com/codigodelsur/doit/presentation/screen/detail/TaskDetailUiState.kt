package com.codigodelsur.doit.presentation.screen.detail

import com.codigodelsur.doit.presentation.model.PTask

data class TaskDetailUiState(
    val task: PTask? = null,
    val goalsExpanded: Boolean = false,
    val showDeleteTaskDialog: Boolean = false,
    val taskDeleted: Boolean = false,
)
