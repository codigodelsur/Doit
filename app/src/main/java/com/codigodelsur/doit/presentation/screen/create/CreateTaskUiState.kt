package com.codigodelsur.doit.presentation.screen.create

data class CreateTaskUiState(
    val title: String = "",
    val description: String = "",
    val date: String = "",
    val goal: String = "",
    val goals: List<String> = emptyList(),
    val taskCreated: Boolean = false,
)
