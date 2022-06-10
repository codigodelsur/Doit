package com.codigodelsur.doit.domain.interactor

import com.codigodelsur.doit.data.repository.TaskRepository
import com.codigodelsur.doit.domain.model.Task
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ObserveTaskUseCase @Inject constructor(
    private val taskRepository: TaskRepository,
) {
    suspend operator fun invoke(id: Long): Flow<Task?> {
        return taskRepository.observeTask(id = id)
    }
}
