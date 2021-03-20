package br.com.alura.spring.kotlin.resource

import br.com.alura.spring.kotlin.domain.dto.TopicoAtualizacaoDto
import br.com.alura.spring.kotlin.domain.dto.TopicoDetalhadoDto
import br.com.alura.spring.kotlin.domain.dto.TopicoRequestDto
import br.com.alura.spring.kotlin.domain.dto.TopicoResponseDto
import br.com.alura.spring.kotlin.domain.enumerable.StatusTopicoEnum
import br.com.alura.spring.kotlin.domain.sealed.CurtiuOperacao
import br.com.alura.spring.kotlin.repository.CursoRepository
import br.com.alura.spring.kotlin.repository.TopicoRepository
import br.com.alura.spring.kotlin.repository.orm.Curso
import br.com.alura.spring.kotlin.repository.orm.Topico
import br.com.alura.spring.kotlin.service.TopicoService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/topicos"])
class TopicoResource(
    private val service: TopicoService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CacheEvict(value = ["listaDeTopicos"], allEntries = true)
    fun cadastrar(@RequestBody @Valid request: TopicoRequestDto): TopicoResponseDto {
        return service.cadastrar(request)
    }

    @Transactional
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    @CacheEvict(value = ["listaDeTopicos"], allEntries = true)
    fun atualizar(@RequestBody @Valid request: TopicoAtualizacaoDto) : TopicoResponseDto {
        return service.atualizar(request)
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = ["/"])
    fun detalhar(): TopicoDetalhadoDto {
        return TopicoDetalhadoDto(
            id = null,
            titulo = "Alura",
            dataCriacao = LocalDateTime.now(),
            mensagem = "Esse é um topico alura",
            status = StatusTopicoEnum.NAO_RESPONDIDO,
            gostei = 10,
            nomeAutor = "Kleber Nunes"
        )
    }

}