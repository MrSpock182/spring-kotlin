package br.com.alura.spring.kotlin.repository

import br.com.alura.spring.kotlin.repository.orm.Topico
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TopicoRepository : JpaRepository<Topico, Long> {
    fun findByCursoNome(nomeCurso: String, paginacao: Pageable) : Page<Topico>
}