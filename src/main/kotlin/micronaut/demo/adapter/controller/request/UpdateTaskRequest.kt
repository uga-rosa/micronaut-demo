package micronaut.demo.adapter.controller.request

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class UpdateTaskRequest(
    val title: String?,
    val description: String?,
    val isCompleted: Boolean?,
)