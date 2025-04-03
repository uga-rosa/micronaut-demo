package micronaut.demo.adapter.persistence.table

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.annotation.MappedProperty
import micronaut.demo.domain.entity.NewTask
import micronaut.demo.domain.entity.Task
import java.time.Instant

@MappedEntity("tasks")
data class TaskTable(
    @Id
    @GeneratedValue
    val id: Long?,

    @MappedProperty("user_id")
    val userId: Long,

    @MappedProperty("title")
    val title: String,

    @MappedProperty("description")
    val description: String,

    @MappedProperty("is_completed")
    val isCompleted: Boolean,

    @MappedProperty("created_at")
    val createdAt: Instant,

    @MappedProperty("updated_at")
    val updatedAt: Instant,
)

fun NewTask.toTable(): TaskTable =
    TaskTable(
        id = null,
        userId = userId,
        title = title,
        description = description,
        isCompleted = false,
        createdAt = Instant.now(),
        updatedAt = Instant.now(),
    )

fun Task.toTable(): TaskTable =
    TaskTable(
        id = id,
        userId = userId,
        title = title,
        description = description,
        isCompleted = isCompleted,
        createdAt = createdAt,
        updatedAt = updatedAt,
    )

fun TaskTable.toTask(): Task =
    Task(
        id = id ?: throw IllegalStateException("Task Id is null. Maybe not saved yet."),
        userId = userId,
        title = title,
        description = description,
        isCompleted = isCompleted,
        createdAt = createdAt,
        updatedAt = updatedAt,
    )
