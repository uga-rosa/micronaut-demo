package micronaut.demo.adapter.controller.request

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class RegisterRequest(
    val name: String,
    val password: String,
)
