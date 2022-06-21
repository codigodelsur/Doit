package com.codigodelsur.doit.presentation.model

import com.codigodelsur.doit.domain.model.Goal
import com.codigodelsur.doit.domain.model.Task
import java.util.*

data class PTask(
    val id: Long,
    val title: String,
    val description: String,
    val date: Date,
    val goals: List<PGoal>,
    val status: PTaskStatus,
) {

    fun toDomain(): Task {
        return Task(
            id = id,
            title = title,
            description = description,
            date = date,
            goals = goals.map(PGoal::toDomain),
            status = status.toDomain()
        )
    }

    companion object {
        val sample = PTask(
            id = 0,
            title = "Take out the trash",
            description = "Lorem ipsum dolor sit amet, consectetur elit.",
            date = Date(),
            goals = List(size = (1..3).random()) { index ->
                PGoal(
                    id = index.toLong(),
                    taskId = 0,
                    title = "Goal #$index",
                    isCompleted = index % 2 == 0,
                )
            },
            status = PTaskStatus.PENDING,
        )

        val samples: List<PTask> by lazy {
            fun taskGoals(taskId: Long): List<PGoal> = List(size = (1..5).random()) { index ->
                PGoal(
                    id = index.toLong(),
                    taskId = taskId,
                    title = "Goal #$index",
                    isCompleted = index % 2 == 0,
                )
            }

            List(size = 10) { index ->
                val taskId = index.toLong()
                PTask(
                    id = taskId,
                    title = "Task #$index",
                    description = "Lorem ipsum dolor sit amet, consectetur elit.",
                    date = Date(),
                    goals = if (listOf(true, false).random()) taskGoals(taskId) else emptyList(),
                    status = PTaskStatus.values().random(),
                )
            }.sortedBy(PTask::status)
        }

        val samplesGroupedByStatus: Map<PTaskStatus, List<PTask>> by lazy {
            samples.groupBy(PTask::status)
        }
    }
}

fun Task.toPresentation(): PTask = PTask(
    id = id,
    title = title,
    description = description,
    date = date,
    goals = goals.map(Goal::toPresentation),
    status = status.toPresentation()
)
