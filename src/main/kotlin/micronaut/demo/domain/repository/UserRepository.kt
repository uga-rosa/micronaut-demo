package micronaut.demo.domain.repository

import micronaut.demo.domain.entity.NewUser
import micronaut.demo.domain.entity.User

interface UserRepository {
    fun save(user: NewUser): User
    fun findByUsername(username: String): User?
    fun existsByUsername(username: String): Boolean
}
