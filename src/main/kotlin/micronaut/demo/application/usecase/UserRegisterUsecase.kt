package micronaut.demo.application.usecase

import jakarta.inject.Singleton
import micronaut.demo.domain.entity.NewUser
import micronaut.demo.domain.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

interface UserRegisterUsecase {
    fun execute(name: String, password: String)
}

@Singleton
class UserRegisterUsecaseImpl(
    private val userRepository: UserRepository,
) : UserRegisterUsecase {
    private val encoder = BCryptPasswordEncoder()

    override fun execute(name: String, password: String) {
        if (userRepository.existsByUsername(name)) {
            throw IllegalArgumentException("User with name $name already exists")
        }
        val hash = encoder.encode(password)
        val user = NewUser(name, hash)
        userRepository.save(user)
    }
}
