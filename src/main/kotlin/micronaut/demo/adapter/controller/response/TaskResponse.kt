package micronaut.demo.adapter.controller.response

import io.micronaut.serde.annotation.Serdeable
import micronaut.demo.domain.entity.Task

@Serdeable
data class TaskResponse(
    val id: Long,
    val userId: Long,
    val title: String,
    val description: String,
    val isCompleted: Boolean,
    val createdAt: String,
    val updatedAt: String,
)

fun Task.toResponse(): TaskResponse =
    TaskResponse(
        id = id,
        userId = userId,
        title = title,
        description = description,
        isCompleted = isCompleted,
        createdAt = createdAt.toString(),
        updatedAt = updatedAt.toString(),
    )
