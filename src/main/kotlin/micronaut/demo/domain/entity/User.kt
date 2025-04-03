package micronaut.demo.domain.entity

data class User(
    val id: Long,
    val username: String,
    val passwordHash: String,
)

data class NewUser(
    val username: String,
    val passwordHash: String,
)
