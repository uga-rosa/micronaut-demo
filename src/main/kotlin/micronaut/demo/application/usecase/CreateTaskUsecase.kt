package micronaut.demo.application.usecase

import jakarta.inject.Singleton
import micronaut.demo.domain.entity.NewTask
import micronaut.demo.domain.entity.Task
import micronaut.demo.domain.repository.TaskRepository

interface CreateTaskUsecase {
    fun execute(userId: Long, title: String, description: String): Task
}

@Singleton
class CreateTaskUsecaseImpl(
    private val taskRepository: TaskRepository,
) : CreateTaskUsecase {
    override fun execute(userId: Long, title: String, description: String): Task {
        val newTask = NewTask(userId, title, description)
        return taskRepository.save(newTask)
    }
}
