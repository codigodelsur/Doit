package com.codigodelsur.doit.presentation.model

import com.codigodelsur.doit.domain.model.Goal

data class PGoal(
    val id: Long,
    val taskId: Long,
    val title: String,
    val isCompleted: Boolean,
)

fun Goal.toPresentation(): PGoal = PGoal(
    id = id,
    taskId = taskId,
    title = title,
    isCompleted = isCompleted,
)
