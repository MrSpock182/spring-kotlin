package br.com.alura.spring.kotlin.domain.dto

import br.com.alura.spring.kotlin.domain.sealed.CurtiuOperacao
import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotEmpty

data class TopicoAtualizacaoDto(
    val id: Long,
    @NotEmpty @Length(min = 5)
    val titulo: String,
    @NotEmpty @Length(min = 10)
    val mensagem: String,
    val curtiu: CurtiuOperacao
)
