package br.com.alura.spring.kotlin.resource

import br.com.alura.spring.kotlin.domain.dto.DetalhesDoTopicoDto
import br.com.alura.spring.kotlin.domain.dto.TopicoRequestDto
import br.com.alura.spring.kotlin.domain.enumerable.StatusTopicoEnum
import org.springframework.cache.annotation.CacheEvict
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import javax.validation.Valid

@RestController
@RequestMapping(value = ["/topicos"])
class TopicoResource {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CacheEvict(value = ["listaDeTopicos"], allEntries = true)
    fun cadastrar(@RequestBody @Valid request: TopicoRequestDto) : String {
        return request.mensagem
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = ["/"])
    fun detalhar() : DetalhesDoTopicoDto {
        return DetalhesDoTopicoDto(
            id = null,
            titulo = "Alura",
            dataCriacao = LocalDateTime.now(),
            mensagem = "Esse é um topico alura",
            status = StatusTopicoEnum.NAO_RESPONDIDO,
            nomeAutor = "Kleber Nunes"
        )
    }

}