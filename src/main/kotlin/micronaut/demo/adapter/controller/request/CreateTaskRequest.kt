package micronaut.demo.adapter.controller.request

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class CreateTaskRequest(
    val title: String,
    val description: String,
)
