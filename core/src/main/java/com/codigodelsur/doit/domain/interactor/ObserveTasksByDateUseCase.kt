package com.codigodelsur.doit.domain.interactor

import com.codigodelsur.doit.data.repository.TaskRepository
import com.codigodelsur.doit.domain.model.Task
import java.util.*
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class ObserveTasksByDateUseCase @Inject constructor(
    private val taskRepository: TaskRepository,
) {
    suspend operator fun invoke(date: Date): Flow<List<Task>> {
        return taskRepository.observeTasksByDate(date = date)
    }
}
