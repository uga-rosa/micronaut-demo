package micronaut.demo.adapter.persistence

import jakarta.inject.Singleton
import micronaut.demo.adapter.persistence.crud.TaskCrudRepository
import micronaut.demo.adapter.persistence.table.toTable
import micronaut.demo.adapter.persistence.table.toTask
import micronaut.demo.domain.entity.NewTask
import micronaut.demo.domain.entity.Task
import micronaut.demo.domain.repository.TaskRepository
import kotlin.jvm.optionals.getOrNull

@Singleton
class TaskRepositoryImpl(
    private val taskCrudRepository: TaskCrudRepository,
) : TaskRepository {
    override fun save(task: NewTask): Task {
        val table = task.toTable()
        return taskCrudRepository.save(table)
            .toTask()
    }

    override fun findById(id: Long): Task? {
        return taskCrudRepository.findById(id).getOrNull()
            ?.toTask()
    }

    override fun findByUserId(userId: Long): List<Task> {
        return taskCrudRepository.findByUserId(userId)
            .map { it.toTask() }
    }

    override fun update(task: Task): Task {
        val table = task.toTable()
        return taskCrudRepository.update(table)
            .toTask()
    }

    override fun deleteById(id: Long): Boolean {
        if (!taskCrudRepository.existsById(id)) {
            return false
        }
        taskCrudRepository.deleteById(id)
        return true
    }
}
