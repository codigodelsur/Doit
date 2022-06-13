package com.codigodelsur.doit.presentation.model

import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.codigodelsur.doit.R
import com.codigodelsur.doit.domain.model.TaskStatus
import com.codigodelsur.doit.presentation.theme.BlueCrayola
import com.codigodelsur.doit.presentation.theme.Maize
import com.codigodelsur.doit.presentation.theme.MountainMeadow

enum class PTaskStatus(
    @StringRes val labelRes: Int,
    val color: Color,
) {
    PENDING(
        labelRes = R.string.task_status_pending,
        color = Maize,
    ),

    IN_PROGRESS(
        labelRes = R.string.task_status_in_progress,
        color = BlueCrayola,
    ),

    COMPLETED(
        labelRes = R.string.task_status_completed,
        color = MountainMeadow,
    )
}

fun TaskStatus.toPresentation(): PTaskStatus {
    return when (this) {
        TaskStatus.PENDING -> PTaskStatus.PENDING
        TaskStatus.IN_PROGRESS -> PTaskStatus.IN_PROGRESS
        TaskStatus.COMPLETED -> PTaskStatus.COMPLETED
    }
}
