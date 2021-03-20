package br.com.alura.spring.kotlin.service

import br.com.alura.spring.kotlin.domain.dto.TopicoRequestDto
import br.com.alura.spring.kotlin.domain.dto.TopicoResponseDto

interface TopicoService {
    fun cadastrar(request: TopicoRequestDto) : TopicoResponseDto
}