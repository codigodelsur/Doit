package com.codigodelsur.doit.data.framework.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.codigodelsur.doit.data.framework.db.model.LocalGoal
import com.codigodelsur.doit.data.framework.db.model.LocalTask
import com.codigodelsur.doit.data.framework.db.model.LocalTaskWithGoals
import java.util.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert
    suspend fun insertTasks(vararg tasks: LocalTask): List<Long>

    @Update
    suspend fun updateTasks(vararg tasks: LocalTask)

    @Delete
    suspend fun deleteTasks(vararg tasks: LocalTask)

    @Insert
    suspend fun insertGoals(vararg goals: LocalGoal)

    @Update
    suspend fun updateGoals(vararg goals: LocalGoal)

    @Delete
    suspend fun deleteGoals(vararg goals: LocalGoal)

    @Transaction
    suspend fun insertTasksWithGoals(vararg tasks: LocalTaskWithGoals) {
        val tasksIds = insertTasks(tasks = tasks.map(LocalTaskWithGoals::task).toTypedArray())

        val tasksGoals = tasks
            .mapIndexed { index, taskWithGoals ->
                taskWithGoals.goals.map { it.copy(taskId = tasksIds[index]) }
            }
            .flatten()
            .toTypedArray()

        insertGoals(goals = tasksGoals)
    }

    @Transaction
    suspend fun updateTasksWithGoals(vararg tasks: LocalTaskWithGoals) {
        updateTasks(tasks = tasks.map(LocalTaskWithGoals::task).toTypedArray())
        updateGoals(goals = tasks.map(LocalTaskWithGoals::goals).flatten().toTypedArray())
    }

    @Transaction
    suspend fun deleteTasksWithGoals(vararg tasks: LocalTaskWithGoals) {
        deleteTasks(tasks = tasks.map(LocalTaskWithGoals::task).toTypedArray())
        deleteGoals(goals = tasks.map(LocalTaskWithGoals::goals).flatten().toTypedArray())
    }

    @Transaction
    @Query("SELECT * FROM tasks WHERE id = :id")
    fun observeTaskWithGoals(id: Long): Flow<LocalTaskWithGoals?>

    @Transaction
    @Query("SELECT * FROM tasks")
    fun observeTasksWithGoals(): Flow<List<LocalTaskWithGoals>>

    @Transaction
    @Query("SELECT * FROM tasks WHERE date(date/1000, 'unixepoch') = date(:date/1000, 'unixepoch')")
    fun observeTasksWithGoalsByDate(date: Date): Flow<List<LocalTaskWithGoals>>

}
