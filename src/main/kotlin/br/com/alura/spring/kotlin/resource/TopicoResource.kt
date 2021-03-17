package br.com.alura.spring.kotlin.resource

import br.com.alura.spring.kotlin.domain.TopicoRequestDto
import org.springframework.cache.annotation.CacheEvict
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
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

}