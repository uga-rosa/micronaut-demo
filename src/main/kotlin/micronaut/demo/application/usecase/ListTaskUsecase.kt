package micronaut.demo.application.usecase

import jakarta.inject.Singleton
import micronaut.demo.domain.entity.Task
import micronaut.demo.domain.repository.TaskRepository

interface ListTaskUsecase {
    fun execute(userId: Long): List<Task>
}

@Singleton
class ListTaskUsecaseImpl(
    private val taskRepository: TaskRepository,
) : ListTaskUsecase {
    override fun execute(userId: Long): List<Task> {
        return taskRepository.findByUserId(userId)
    }
}
