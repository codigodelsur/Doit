package com.codigodelsur.doit.presentation.screen.home

import com.codigodelsur.doit.presentation.model.PTask
import com.codigodelsur.doit.presentation.model.PTaskStatus

data class HomeUiState(
    val tasksGroupedByStatus: Map<PTaskStatus, List<PTask>> = emptyMap(),
)
