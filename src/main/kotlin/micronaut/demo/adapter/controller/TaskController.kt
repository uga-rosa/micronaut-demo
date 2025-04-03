package micronaut.demo.adapter.controller

import io.micronaut.http.MediaType
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Consumes
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Patch
import io.micronaut.http.annotation.PathVariable
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Produces
import io.micronaut.security.annotation.Secured
import io.micronaut.security.authentication.Authentication
import io.micronaut.security.rules.SecurityRule
import micronaut.demo.adapter.controller.request.CreateTaskRequest
import micronaut.demo.adapter.controller.request.UpdateTaskRequest
import micronaut.demo.adapter.controller.response.TaskResponse
import micronaut.demo.adapter.controller.response.toResponse
import micronaut.demo.application.usecase.CreateTaskUsecase
import micronaut.demo.application.usecase.DeleteTaskUsecase
import micronaut.demo.application.usecase.ListTaskUsecase
import micronaut.demo.application.usecase.UpdateTaskUsecase

@Controller("/tasks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Secured(SecurityRule.IS_AUTHENTICATED)
class TaskController(
    private val createTaskUsecase: CreateTaskUsecase,
    private val listTaskUsecase: ListTaskUsecase,
    private val updateTaskUsecase: UpdateTaskUsecase,
    private val deleteTaskUsecase: DeleteTaskUsecase,
) {
    @Post
    fun createTask(
        authentication: Authentication,
        @Body body: CreateTaskRequest,
    ): TaskResponse {
        val userId = extractUserId(authentication)
        val task = createTaskUsecase.execute(userId, body.title, body.description)
        return task.toResponse()
    }

    @Get
    fun listTasks(
        authentication: Authentication,
    ): List<TaskResponse> {
        val userId = extractUserId(authentication)
        return listTaskUsecase.execute(userId)
            .map { it.toResponse() }
    }

    @Patch("/{id}")
    @Consumes(MediaType.APPLICATION_JSON_MERGE_PATCH)
    fun updateTask(
        authentication: Authentication,
        @PathVariable id: Long,
        @Body body: UpdateTaskRequest,
    ): TaskResponse {
        val userId = extractUserId(authentication)
        val task = updateTaskUsecase.execute(userId, id, body.title, body.description, body.isCompleted)
        return task.toResponse()
    }

    @Delete("/{id}")
    fun deleteTask(
        authentication: Authentication,
        @PathVariable id: Long,
    ) {
        val userId = extractUserId(authentication)
        deleteTaskUsecase.execute(userId, id)
    }

    private fun extractUserId(authentication: Authentication): Long {
        return authentication.attributes["userId"] as? Long
            ?: throw IllegalArgumentException("User ID not found in authentication attributes")
    }
}
