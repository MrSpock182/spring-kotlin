package br.com.alura.spring.kotlin.domain.dto

import br.com.alura.spring.kotlin.domain.enumerable.StatusTopicoEnum
import java.time.LocalDateTime

data class TopicoDetalhadoDto(
    val id: Long?,
    val titulo: String,
    val mensagem: String,
    val dataCriacao: LocalDateTime,
    val nomeAutor: String,
    val status: StatusTopicoEnum,
)
