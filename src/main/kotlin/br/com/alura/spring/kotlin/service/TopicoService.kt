package br.com.alura.spring.kotlin.service

import br.com.alura.spring.kotlin.domain.dto.TopicoAtualizacaoDto
import br.com.alura.spring.kotlin.domain.dto.TopicoDetalhadoDto
import br.com.alura.spring.kotlin.domain.dto.TopicoRequestDto
import br.com.alura.spring.kotlin.domain.dto.TopicoResponseDto

interface TopicoService {
    fun cadastrar(request: TopicoRequestDto) : TopicoResponseDto

    fun atualizar(request: TopicoAtualizacaoDto) : TopicoResponseDto

    fun remover(id: Long)

    fun detalhar(id: Long): TopicoDetalhadoDto
}