package br.com.alura.spring.kotlin.repository

import br.com.alura.spring.kotlin.repository.orm.Usuario
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UsuarioRepository : JpaRepository<Usuario, Long> {
    fun findByEmail(email: String?) : Optional<Usuario>
}