package micronaut.demo.domain.entity

import java.time.Instant

data class Task(
    val id: Long,
    val userId: Long,
    val title: String,
    val description: String,
    val isCompleted: Boolean,
    val createdAt: Instant,
    val updatedAt: Instant,
)

data class NewTask(
    val userId: Long,
    val title: String,
    val description: String,
)
