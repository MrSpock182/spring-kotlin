package br.com.alura.spring.kotlin.domain.dto

import br.com.alura.spring.kotlin.domain.sealed.CurtiuOperacao
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotEmpty

data class TopicoRequestDto(
    @NotEmpty @Length(min = 5)
    val titulo: String,
    @NotEmpty @Length(min = 10)
    val mensagem: String,
    @NotEmpty
    val nomeCurso: String,
    val curtiu: CurtiuOperacao
)