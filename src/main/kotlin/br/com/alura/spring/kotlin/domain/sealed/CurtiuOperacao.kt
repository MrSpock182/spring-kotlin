package br.com.alura.spring.kotlin.domain.sealed

import com.fasterxml.jackson.annotation.JsonCreator

sealed class CurtiuOperacao {

    private companion object {
        @JsonCreator
        @JvmStatic
        fun procurarAtributo(simpleName: String): CurtiuOperacao? {
            return CurtiuOperacao::class.sealedSubclasses.first {
                it.simpleName == simpleName
            }.objectInstance
        }
    }

    object Curtiu : CurtiuOperacao()
    object NaoCurtiu : CurtiuOperacao()
}
