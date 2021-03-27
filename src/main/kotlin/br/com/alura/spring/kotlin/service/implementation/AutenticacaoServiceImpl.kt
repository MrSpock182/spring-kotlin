package br.com.alura.spring.kotlin.service.implementation

import br.com.alura.spring.kotlin.exception.NotFoundException
import br.com.alura.spring.kotlin.repository.UsuarioRepository
import br.com.alura.spring.kotlin.repository.orm.Usuario
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.util.*

@Service
class AutenticacaoServiceImpl(private val repository: UsuarioRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        val usuario: Optional<Usuario> = repository.findByEmail(username)
        if(usuario.isEmpty) {
            throw NotFoundException("Usuário não encontrado")
        }
        return usuario.get()
    }
}