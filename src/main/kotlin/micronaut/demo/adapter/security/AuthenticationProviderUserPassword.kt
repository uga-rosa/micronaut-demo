package micronaut.demo.adapter.security

import io.micronaut.http.HttpRequest
import io.micronaut.security.authentication.AuthenticationFailed
import io.micronaut.security.authentication.AuthenticationRequest
import io.micronaut.security.authentication.AuthenticationResponse
import io.micronaut.security.authentication.provider.HttpRequestAuthenticationProvider
import jakarta.inject.Singleton
import micronaut.demo.domain.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Singleton
class AuthenticationProviderUserPassword<B>(
    private val userRepository: UserRepository,
) : HttpRequestAuthenticationProvider<B> {
    private val passwordEncoder = BCryptPasswordEncoder()

    override fun authenticate(
        requestContext: HttpRequest<B>?,
        authRequest: AuthenticationRequest<String, String>
    ): AuthenticationResponse {
        val username = authRequest.identity
        val password = authRequest.secret

        val user = userRepository.findByUsername(username)
        if (user != null && passwordEncoder.matches(password, user.passwordHash)) {
            return AuthenticationResponse.success(username, listOf("ROLE_USER"), mapOf("userId" to user.id))
        } else {
            return AuthenticationFailed()
        }
    }
}
