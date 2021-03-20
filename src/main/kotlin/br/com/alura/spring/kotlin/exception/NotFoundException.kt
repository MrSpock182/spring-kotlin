package br.com.alura.spring.kotlin.exception

class NotFoundException : RuntimeException {
    constructor(message: String): super(message)
    constructor(message: String, ex: Exception): super(message, ex)
    constructor(ex: Exception): super(ex)
}