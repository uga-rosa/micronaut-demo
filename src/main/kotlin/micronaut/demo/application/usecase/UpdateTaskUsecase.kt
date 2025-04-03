package micronaut.demo.application.usecase

import jakarta.inject.Singleton
import micronaut.demo.domain.entity.Task
import micronaut.demo.domain.repository.TaskRepository

interface UpdateTaskUsecase {
    fun execute(
        userId: Long,
        id: Long,
        title: String?,
        description: String?,
        isCompleted: Boolean?,
    ): Task
}

@Singleton
class UpdateTaskUsecaseImpl(
    private val taskRepository: TaskRepository,
) : UpdateTaskUsecase {
    override fun execute(
        userId: Long,
        id: Long,
        title: String?,
        description: String?,
        isCompleted: Boolean?,
    ): Task {
        val task = taskRepository.findById(id)
            ?: throw IllegalArgumentException("Task with id $id not found")
        if (task.userId != userId) {
            throw IllegalArgumentException("Task with id $id does not belong to user $userId")
        }
        val updatedTask = task.copy(
            title = title ?: task.title,
            description = description ?: task.description,
            isCompleted = isCompleted ?: task.isCompleted,
        )
        return taskRepository.update(updatedTask)
    }
}
