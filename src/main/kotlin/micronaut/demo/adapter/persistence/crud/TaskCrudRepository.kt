package micronaut.demo.adapter.persistence.crud

import io.micronaut.data.jdbc.annotation.JdbcRepository
import io.micronaut.data.model.query.builder.sql.Dialect
import io.micronaut.data.repository.CrudRepository
import micronaut.demo.adapter.persistence.table.TaskTable

@JdbcRepository(dialect = Dialect.POSTGRES)
interface TaskCrudRepository : CrudRepository<TaskTable, Long> {
    fun findByUserId(userId: Long): List<TaskTable>
}