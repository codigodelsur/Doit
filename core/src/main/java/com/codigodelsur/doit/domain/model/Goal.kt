package com.codigodelsur.doit.domain.model

data class Goal(
    val id: Long,
    val taskId: Long,
    val title: String,
    val isCompleted: Boolean,
)
