package com.codigodelsur.doit.data.source

import com.codigodelsur.doit.domain.model.Task
import java.util.*
import kotlinx.coroutines.flow.Flow

interface TaskLocalDataSource {
    suspend fun insertTasks(vararg tasks: Task)
    suspend fun updateTasks(vararg tasks: Task)
    suspend fun deleteTasks(vararg tasks: Task)
    suspend fun observeTask(id: Long): Flow<Task?>
    suspend fun observeTasks(): Flow<List<Task>>
    suspend fun observeTasksByDate(date: Date): Flow<List<Task>>
}
