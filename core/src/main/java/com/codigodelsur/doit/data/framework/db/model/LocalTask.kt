package com.codigodelsur.doit.data.framework.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.codigodelsur.doit.domain.model.Goal
import com.codigodelsur.doit.domain.model.Task
import com.codigodelsur.doit.domain.model.TaskStatus
import java.util.*

@Entity(tableName = "tasks")
data class LocalTask(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "date")
    val date: Date,
    @ColumnInfo(name = "status")
    val status: TaskStatus,
)

fun Task.toLocal(): LocalTaskWithGoals = LocalTaskWithGoals(
    task = LocalTask(
        id = id,
        title = title,
        description = description,
        date = date,
        status = status,
    ),
    goals = goals.map(Goal::toLocal),
)
