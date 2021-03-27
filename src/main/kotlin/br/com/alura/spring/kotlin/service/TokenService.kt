package br.com.alura.spring.kotlin.service

import br.com.alura.spring.kotlin.repository.orm.Usuario

interface TokenService {
    fun gerarToken(usuario: Usuario) : String
    fun isTokenValido(token: String?) : Boolean
    fun getIdUsuario(token: String?) : Long
}