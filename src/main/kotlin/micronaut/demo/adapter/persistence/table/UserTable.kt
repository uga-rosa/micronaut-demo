package micronaut.demo.adapter.persistence.table

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.annotation.MappedProperty
import micronaut.demo.domain.entity.NewUser
import micronaut.demo.domain.entity.User

@MappedEntity("users")
data class UserTable(
    @Id
    @GeneratedValue
    val id: Long?,

    @MappedProperty("username")
    val username: String,

    @MappedProperty("password_hash")
    val passwordHash: String,
)

fun NewUser.toTable(): UserTable =
    UserTable(
        id = null,
        username = username,
        passwordHash = passwordHash,
    )

fun UserTable.toUser(): User =
    User(
        id = id ?: throw IllegalStateException("User Id is null. Maybe not saved yet."),
        username = username,
        passwordHash = passwordHash,
    )
