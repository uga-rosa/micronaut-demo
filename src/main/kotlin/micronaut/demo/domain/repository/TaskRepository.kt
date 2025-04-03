package micronaut.demo.domain.repository

import micronaut.demo.domain.entity.NewTask
import micronaut.demo.domain.entity.Task

interface TaskRepository {
    fun save(task: NewTask): Task
    fun findById(id: Long): Task?
    fun findByUserId(userId: Long): List<Task>
    fun update(task: Task): Task
    fun deleteById(id: Long): Boolean
}
