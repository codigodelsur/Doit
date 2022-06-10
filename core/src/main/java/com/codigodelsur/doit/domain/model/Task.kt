package com.codigodelsur.doit.domain.model

import java.util.*

data class Task(
    val id: Long,
    val title: String,
    val description: String,
    val date: Date,
    val goals: List<Goal>,
    val status: TaskStatus,
)
