package com.codigodelsur.doit.data.source

import com.codigodelsur.doit.data.framework.db.dao.TaskDao
import com.codigodelsur.doit.data.framework.db.model.LocalTaskWithGoals
import com.codigodelsur.doit.data.framework.db.model.toLocal
import com.codigodelsur.doit.domain.model.Task
import java.util.*
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskLocalDataSourceImpl @Inject constructor(
    private val taskDao: TaskDao,
) : TaskLocalDataSource {

    override suspend fun insertTasks(vararg tasks: Task) {
        taskDao.insertTasksWithGoals(tasks = tasks.map(Task::toLocal).toTypedArray())
    }

    override suspend fun updateTasks(vararg tasks: Task) {
        taskDao.updateTasksWithGoals(tasks = tasks.map(Task::toLocal).toTypedArray())
    }

    override suspend fun deleteTasks(vararg tasks: Task) {
        taskDao.deleteTasksWithGoals(tasks = tasks.map(Task::toLocal).toTypedArray())
    }

    override suspend fun observeTask(id: Long): Flow<Task?> {
        return taskDao.observeTaskWithGoals(id = id).map { localTask ->
            localTask?.toDomain()
        }
    }

    override suspend fun observeTasks(): Flow<List<Task>> {
        return taskDao.observeTasksWithGoals()
            .map { task ->
                task.map(LocalTaskWithGoals::toDomain)
            }
    }

    override suspend fun observeTasksByDate(date: Date): Flow<List<Task>> {
        return taskDao
            .observeTasksWithGoalsByDate(date = date)
            .map { task ->
                task.map(LocalTaskWithGoals::toDomain)
            }
    }
}
