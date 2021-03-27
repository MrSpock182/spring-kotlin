package br.com.alura.spring.kotlin.repository.orm

import br.com.alura.spring.kotlin.domain.enumerable.StatusTopicoEnum
import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Topico(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    val titulo: String,
    val mensagem: String,
    val totalGostou: Int,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    @Enumerated(EnumType.STRING)
    val status: StatusTopicoEnum = StatusTopicoEnum.NAO_RESPONDIDO,
    @ManyToOne
    val autor: Usuario? = null,
    @ManyToOne
    val curso: Curso,
    @OneToMany(mappedBy = "topico")
    val respostas: List<Resposta> = ArrayList()
)
