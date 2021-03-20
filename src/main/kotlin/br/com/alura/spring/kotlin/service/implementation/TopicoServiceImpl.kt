package br.com.alura.spring.kotlin.service.implementation

import br.com.alura.spring.kotlin.domain.dto.TopicoRequestDto
import br.com.alura.spring.kotlin.domain.dto.TopicoResponseDto
import br.com.alura.spring.kotlin.repository.CursoRepository
import br.com.alura.spring.kotlin.repository.TopicoRepository
import br.com.alura.spring.kotlin.repository.orm.Topico
import br.com.alura.spring.kotlin.service.TopicoService
import org.springframework.stereotype.Service

@Service
class TopicoServiceImpl(
    private val topicoRepository: TopicoRepository,
    private val cursoRepository: CursoRepository
) : TopicoService {

    override fun cadastrar(request: TopicoRequestDto): TopicoResponseDto {
        val topico = Topico(
            id = null,
            titulo = request.titulo,
            mensagem = request.mensagem,
            totalGostou = 0,
            curso = cursoRepository.findByNome(request.nomeCurso)
        )

        val topicoSalvo = topicoRepository.save(topico)

        return TopicoResponseDto(
            id = topicoSalvo.id,
            titulo = topicoSalvo.titulo,
            mensagem = topicoSalvo.mensagem,
            dataCriacao = topicoSalvo.dataCriacao
        )
    }

}