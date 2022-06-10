package com.codigodelsur.doit.data.repository

import com.codigodelsur.doit.data.source.TaskLocalDataSource
import com.codigodelsur.doit.domain.model.Task
import java.util.*
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class TaskRepository @Inject constructor(
    private val taskLocalDataSource: TaskLocalDataSource,
) {
    suspend fun createTasks(vararg tasks: Task) {
        taskLocalDataSource.insertTasks(tasks = tasks)
    }

    suspend fun updateTasks(vararg tasks: Task) {
        taskLocalDataSource.updateTasks(tasks = tasks)
    }

    suspend fun deleteTasks(vararg tasks: Task) {
        taskLocalDataSource.deleteTasks(tasks = tasks)
    }

    suspend fun observeTask(id: Long): Flow<Task?> {
        return taskLocalDataSource.observeTask(id = id)
    }

    suspend fun observeTasks(): Flow<List<Task>> {
        return taskLocalDataSource.observeTasks()
    }

    suspend fun observeTasksByDate(date: Date): Flow<List<Task>> {
        return taskLocalDataSource.observeTasksByDate(date = date)
    }
}
