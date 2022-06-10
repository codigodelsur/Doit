package com.codigodelsur.doit.domain.interactor

import com.codigodelsur.doit.data.repository.TaskRepository
import com.codigodelsur.doit.domain.model.Goal
import com.codigodelsur.doit.domain.model.Task
import com.codigodelsur.doit.domain.model.TaskStatus
import java.util.*
import javax.inject.Inject

class CreateTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository,
) {
    suspend operator fun invoke(
        id: Long = 0,
        title: String,
        description: String,
        date: Date,
        goals: List<String>,
        status: TaskStatus = TaskStatus.PENDING,
    ) {
        val task = Task(
            id = id,
            title = title,
            description = description,
            date = date,
            goals = goals.map { goalTitle ->
                Goal(
                    id = 0,
                    taskId = id,
                    title = goalTitle,
                    isCompleted = false,
                )
            },
            status = status,
        )
        taskRepository.createTasks(tasks = arrayOf(task))
    }
}
