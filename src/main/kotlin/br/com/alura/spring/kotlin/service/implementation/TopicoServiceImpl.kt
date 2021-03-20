package br.com.alura.spring.kotlin.service.implementation

import br.com.alura.spring.kotlin.domain.dto.TopicoAtualizacaoDto
import br.com.alura.spring.kotlin.domain.dto.TopicoDetalhadoDto
import br.com.alura.spring.kotlin.domain.dto.TopicoRequestDto
import br.com.alura.spring.kotlin.domain.dto.TopicoResponseDto
import br.com.alura.spring.kotlin.domain.sealed.CurtiuOperacao
import br.com.alura.spring.kotlin.exception.NotFoundException
import br.com.alura.spring.kotlin.repository.CursoRepository
import br.com.alura.spring.kotlin.repository.TopicoRepository
import br.com.alura.spring.kotlin.repository.orm.Topico
import br.com.alura.spring.kotlin.service.TopicoService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

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

    override fun atualizar(request: TopicoAtualizacaoDto): TopicoResponseDto {
        val optional: Optional<Topico> = topicoRepository.findById(request.id)

        if (optional.isEmpty) {
            throw NotFoundException("Topico não encontrado")
        }

        val topicoPorId = optional.get()
        val topicoSalvar = Topico(
            id = topicoPorId.id,
            mensagem = topicoPorId.mensagem,
            dataCriacao = topicoPorId.dataCriacao,
            titulo = topicoPorId.titulo,
            status = topicoPorId.status,
            curso = topicoPorId.curso,
            respostas = topicoPorId.respostas,
            totalGostou = calculaGostou(topicoPorId.totalGostou, request.curtiu)
        )
        val topicoSalvo = topicoRepository.save(topicoSalvar)

        return TopicoResponseDto(
            id = topicoSalvo.id,
            titulo = topicoSalvo.titulo,
            mensagem = topicoSalvo.mensagem,
            dataCriacao = topicoSalvo.dataCriacao
        )
    }

    override fun remover(id: Long) {
        val optional: Optional<Topico> = topicoRepository.findById(id)

        if (optional.isEmpty) {
            throw NotFoundException("Topico não encontrado")
        }

        topicoRepository.deleteById(id)
    }

    override fun detalhar(id: Long): TopicoDetalhadoDto {
        val optional: Optional<Topico> = topicoRepository.findById(id)

        if (optional.isEmpty) {
            throw NotFoundException("Topico não encontrado")
        }

        val value: Topico = optional.get()
        return TopicoDetalhadoDto(
            id = value.id,
            titulo = value.titulo,
            mensagem = value.mensagem,
            dataCriacao = value.dataCriacao,
            gostei = value.totalGostou,
            status = value.status
        )
    }

    private fun calculaGostou(x: Int, op: CurtiuOperacao): Int = when (op) {
        is CurtiuOperacao.Curtiu -> x + 1
        is CurtiuOperacao.NaoCurtiu -> x - 1
    }

    override fun listar(nomeCurso: String?, paginacao: Pageable): Page<TopicoResponseDto> {
        return if (nomeCurso == null) {
            cast(topicoRepository.findAll(paginacao))
        } else {
            cast(topicoRepository.findByCursoNome(paginacao = paginacao, nomeCurso = nomeCurso))
        }
    }

    private fun cast(page: Page<Topico>) : Page<TopicoResponseDto> {
        return page.map { v ->
            TopicoResponseDto(
                id = v.id,
                titulo = v.titulo,
                mensagem = v.mensagem,
                dataCriacao = v.dataCriacao
            )
        }
    }
}