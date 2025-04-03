package micronaut.demo.application.usecase

import jakarta.inject.Singleton
import micronaut.demo.domain.repository.TaskRepository

interface DeleteTaskUsecase {
    fun execute(userId: Long, id: Long): Unit
}

@Singleton
class DeleteTaskUsecaseImpl(
    private val taskRepository: TaskRepository,
) : DeleteTaskUsecase {
    override fun execute(userId: Long, id: Long) {
        val task = taskRepository.findById(id)
            ?: throw IllegalArgumentException("Task with id $id not found")
        if (task.userId != userId) {
            throw IllegalArgumentException("Task with id $id does not belong to user $userId")
        }
        if (!taskRepository.deleteById(id)) {
            throw IllegalArgumentException("Failed to delete task with id $id")
        }
    }
}
