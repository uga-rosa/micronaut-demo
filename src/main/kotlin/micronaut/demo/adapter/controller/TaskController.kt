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
import micronaut.demo.application.usecase.CreateTaskUsecase
import micronaut.demo.application.usecase.DeleteTaskUsecase
import micronaut.demo.application.usecase.ListTaskUsecase
import micronaut.demo.application.usecase.UpdateTaskUsecase
import micronaut.demo.adapter.controller.request.CreateTaskRequest
import micronaut.demo.adapter.controller.request.UpdateTaskRequest
import micronaut.demo.adapter.controller.response.TaskResponse

@Controller("/tasks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
class TaskController(
    private val createTaskUsecase: CreateTaskUsecase,
    private val listTaskUsecase: ListTaskUsecase,
    private val updateTaskUsecase: UpdateTaskUsecase,
    private val deleteTaskUsecase: DeleteTaskUsecase,
) {
    @Post
    fun createTask(
        @Body body: CreateTaskRequest,
    ): TaskResponse {
        TODO()
    }

    @Get
    fun listTasks(): List<TaskResponse> {
        TODO()
    }

    @Patch("/{id}")
    @Consumes(MediaType.APPLICATION_JSON_MERGE_PATCH)
    fun updateTask(
        @PathVariable id: Long,
        @Body body: UpdateTaskRequest,
    ): TaskResponse {
        TODO()
    }

    @Delete("/{id}")
    fun deleteTask(
        @PathVariable id: Long,
    ) {
        TODO()
    }
}
