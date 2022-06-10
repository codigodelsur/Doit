package com.codigodelsur.doit.data.framework.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.codigodelsur.doit.domain.model.Goal

@Entity(tableName = "goals")
data class LocalGoal(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "task_id")
    val taskId: Long,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "is_completed")
    val isCompleted: Boolean,
) {
    fun toDomain(): Goal = Goal(
        id = id,
        taskId = taskId,
        title = title,
        isCompleted = isCompleted,
    )
}

fun Goal.toLocal(): LocalGoal = LocalGoal(
    id = id,
    taskId = taskId,
    title = title,
    isCompleted = isCompleted,
)
