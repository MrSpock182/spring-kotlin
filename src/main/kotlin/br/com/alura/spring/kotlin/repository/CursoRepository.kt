package br.com.alura.spring.kotlin.repository

import br.com.alura.spring.kotlin.repository.orm.Curso
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CursoRepository : JpaRepository<Curso, Long> {
}