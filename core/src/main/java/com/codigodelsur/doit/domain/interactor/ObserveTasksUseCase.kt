package com.codigodelsur.doit.domain.interactor

import com.codigodelsur.doit.data.repository.TaskRepository
import com.codigodelsur.doit.domain.model.Task
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ObserveTasksUseCase @Inject constructor(
    private val taskRepository: TaskRepository,
) {
    suspend operator fun invoke(): Flow<List<Task>> {
        return taskRepository.observeTasks()
    }
}
