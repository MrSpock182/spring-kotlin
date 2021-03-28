package br.com.alura.spring.kotlin.service

import br.com.alura.spring.kotlin.SpringKotlinApplicationTests
import br.com.alura.spring.kotlin.domain.dto.TopicoAtualizacaoDto
import br.com.alura.spring.kotlin.domain.dto.TopicoRequestDto
import br.com.alura.spring.kotlin.domain.sealed.CurtiuOperacao
import br.com.alura.spring.kotlin.exception.InternalServerError
import br.com.alura.spring.kotlin.exception.NotFoundException
import br.com.alura.spring.kotlin.repository.CursoRepository
import br.com.alura.spring.kotlin.repository.TopicoRepository
import br.com.alura.spring.kotlin.repository.orm.Curso
import br.com.alura.spring.kotlin.repository.orm.Topico
import br.com.alura.spring.kotlin.repository.orm.Usuario
import br.com.alura.spring.kotlin.service.implementation.TopicoServiceImpl
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

import org.mockito.Mockito.*
import java.util.*

@RunWith(MockitoJUnitRunner::class)
class TopicoServiceTest : SpringKotlinApplicationTests() {

    @InjectMocks
    lateinit var service: TopicoServiceImpl

    @Mock
    lateinit var topicoRepository: TopicoRepository

    @Mock
    lateinit var cursoRepository: CursoRepository

    override fun init() {}

    fun getCurso(): Curso {
        return Curso(
            id = 1L,
            nome = "ALURA KOTLIN",
            categoria = "SPRING"
        )
    }

    fun getTopico(): Topico {
        return Topico(
            id = 1L,
            titulo = "DUVIDA",
            mensagem = "COMO FAZER TESTE NO KOTLIN",
            totalGostou = 0,
            autor = Usuario(1L
                ,"KLEBER"
                ,"kleber@email.com"
                ,"senha"
                , Collections.emptyList()
            ),
            curso = getCurso()
        )
    }

    fun getTopicoRequestDto(): TopicoRequestDto {
        return TopicoRequestDto(
            titulo = "DUVIDA",
            mensagem = "COMO FAZER TESTE NO KOTLIN",
            nomeCurso = "ALURA KOTLIN"
        )
    }

    @Test
    fun cadastrarSucessoTest() {
        `when`(cursoRepository.findByNome("ALURA KOTLIN")).thenReturn(getCurso())
        `when`(topicoRepository.save(any())).thenReturn(getTopico())

        service.cadastrar(getTopicoRequestDto())

        verify(cursoRepository).findByNome("ALURA KOTLIN")
        verify(topicoRepository).save(any())
    }

    @Test(expected = InternalServerError::class)
    fun cadastrarErroTest() {
        `when`(cursoRepository.findByNome("ALURA KOTLIN"))
            .thenThrow(InternalServerError::class.java)

        service.cadastrar(getTopicoRequestDto())

        verify(cursoRepository).findByNome("ALURA KOTLIN")
        verify(topicoRepository).save(any())
    }

    fun getTopicoAtualizacaoDto() : TopicoAtualizacaoDto {
        return TopicoAtualizacaoDto(
            id = 1L,
            titulo = "DUVIDA",
            mensagem = "COMO FAZER TESTE NO KOTLIN",
            curtiu = CurtiuOperacao.Curtiu
        )
    }

    @Test
    fun atualizarSucessoTest() {
        `when`(topicoRepository.findById(anyLong())).thenReturn(Optional.of(getTopico()))
        `when`(topicoRepository.save(any())).thenReturn(getTopico())

        service.atualizar(getTopicoAtualizacaoDto())

        verify(topicoRepository).findById(anyLong())
        verify(topicoRepository).save(any())
    }

    @Test(expected = InternalServerError::class)
    fun atualizarErroTest() {
        `when`(topicoRepository.findById(anyLong()))
            .thenThrow(InternalServerError::class.java)

        service.atualizar(getTopicoAtualizacaoDto())

        verify(topicoRepository).findById(anyLong())
        verify(topicoRepository).save(any())
    }

    @Test
    fun removerSucessoTest() {
        `when`(topicoRepository.findById(anyLong())).thenReturn(Optional.of(getTopico()))
        doNothing().`when`(topicoRepository).deleteById(anyLong())

        service.remover(1L)

        verify(topicoRepository).findById(anyLong())
        verify(topicoRepository).deleteById(anyLong())
    }

    @Test(expected = InternalServerError::class)
    fun removerErrorTest() {
        `when`(topicoRepository.findById(anyLong()))
            .thenThrow(InternalServerError::class.java)

        service.remover(1L)

        verify(topicoRepository).findById(anyLong())
        verify(topicoRepository).deleteById(anyLong())
    }


    @Test
    fun detalharSucessoTest() {
        `when`(topicoRepository.findById(anyLong())).thenReturn(Optional.of(getTopico()))

        service.detalhar(1L)

        verify(topicoRepository).findById(anyLong())
    }

    @Test(expected = NotFoundException::class)
    fun detalharErrorTest() {
        `when`(topicoRepository.findById(anyLong()))
            .thenReturn(Optional.empty())

        service.remover(1L)

        verify(topicoRepository).findById(anyLong())
    }

}