package br.com.alura.spring.kotlin.repository

import br.com.alura.spring.kotlin.repository.orm.Topico
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TopicoRepository : JpaRepository<Topico, Long> {
}