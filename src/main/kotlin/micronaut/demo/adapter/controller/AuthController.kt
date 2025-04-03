package micronaut.demo.adapter.controller

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Post
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import micronaut.demo.adapter.controller.request.RegisterRequest
import micronaut.demo.application.usecase.UserRegisterUsecase

@Controller("/auth")
class AuthController(
    private val userRegisterUsecase: UserRegisterUsecase,
) {
    @Post("/register")
    @Secured(SecurityRule.IS_ANONYMOUS)
    fun register(
        @Body body: RegisterRequest,
    ) {
        userRegisterUsecase.execute(body.username, body.password)
    }
}
