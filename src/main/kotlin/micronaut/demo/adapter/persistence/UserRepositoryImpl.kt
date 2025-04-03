package micronaut.demo.adapter.persistence

import jakarta.inject.Singleton
import micronaut.demo.adapter.persistence.crud.UserCrudRepository
import micronaut.demo.adapter.persistence.table.toTable
import micronaut.demo.adapter.persistence.table.toUser
import micronaut.demo.domain.entity.NewUser
import micronaut.demo.domain.entity.User
import micronaut.demo.domain.repository.UserRepository

@Singleton
class UserRepositoryImpl(
    private val userCrudRepository: UserCrudRepository,
) : UserRepository {
    override fun save(user: NewUser): User {
        val table = user.toTable()
        return userCrudRepository.save(table)
            .toUser()
    }

    override fun findByUsername(username: String): User? {
        return userCrudRepository.findByUsername(username)
            ?.toUser()
    }

    override fun existsByUsername(username: String): Boolean {
        return userCrudRepository.existsByUsername(username)
    }
}
