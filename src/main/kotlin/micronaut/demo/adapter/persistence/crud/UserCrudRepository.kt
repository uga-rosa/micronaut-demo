package micronaut.demo.adapter.persistence.crud

import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository
import micronaut.demo.adapter.persistence.table.UserTable

@JdbcRepository(dialect = Dialect.POSTGRES)
interface UserCrudRepository : CrudRepository<UserTable, Long> {
    fun findByUsername(name: String): UserTable?
    fun existsByUsername(name: String): Boolean
}
