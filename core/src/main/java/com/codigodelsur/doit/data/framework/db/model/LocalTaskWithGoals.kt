package com.codigodelsur.doit.data.framework.db.model

import androidx.room.Embedded
import androidx.room.Relation
import com.codigodelsur.doit.domain.model.Task

data class LocalTaskWithGoals(
    @Embedded val task: LocalTask,
    @Relation(
        parentColumn = "id",
        entityColumn = "task_id"
    )
    val goals: List<LocalGoal>
) {
    fun toDomain(): Task = Task(
        id = task.id,
        title = task.title,
        description = task.description,
        date = task.date,
        goals = goals.map(LocalGoal::toDomain),
        status = task.status,
    )
}
