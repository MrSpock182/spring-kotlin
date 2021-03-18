package br.com.alura.spring.kotlin.repository.orm

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Resposta(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val mensagem: String,
    @ManyToOne
    val topico: Topico,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    val solucao: Boolean
)
