package br.com.alura.spring.kotlin.resource

import br.com.alura.spring.kotlin.domain.dto.LoginDto
import br.com.alura.spring.kotlin.domain.dto.TokenDto
import br.com.alura.spring.kotlin.repository.UsuarioRepository
import br.com.alura.spring.kotlin.repository.orm.Usuario
import br.com.alura.spring.kotlin.service.TokenService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

@RestController
class AutenticacaoResource(
    private val tokenService: TokenService,
    private val repository: UsuarioRepository
) {

    @PostMapping(value = ["/auth"])
    @ResponseStatus(HttpStatus.OK)
    fun save(@RequestBody @Valid login: LoginDto) : TokenDto {
        val usuario = Usuario(
            nome = login.nome,
            email = login.email,
            senha = login.senha,
        )
        return TokenDto(tokenService.gerarToken(usuario), "Bearer")
    }

}