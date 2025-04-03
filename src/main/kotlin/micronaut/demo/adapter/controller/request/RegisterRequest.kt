package micronaut.demo.adapter.controller.request

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class RegisterRequest(
    val username: String,
    val password: String,
)
