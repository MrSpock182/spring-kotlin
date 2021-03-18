package br.com.alura.spring.kotlin.repository

import br.com.alura.spring.kotlin.repository.orm.Topico
import org.springframework.data.jpa.repository.JpaRepository

interface TopicoRepository : JpaRepository<Topico, Long> {
}