package com.codigodelsur.doit.domain.interactor

import com.codigodelsur.doit.data.repository.TaskRepository
import com.codigodelsur.doit.domain.model.Task
import javax.inject.Inject

class UpdateTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository,
) {
    suspend operator fun invoke(task: Task) {
        taskRepository.updateTasks(tasks = arrayOf(task))
    }
}
