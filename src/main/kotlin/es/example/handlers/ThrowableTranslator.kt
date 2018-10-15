package es.example.handlers

import es.example.exception.PathNotFoundException
import org.springframework.http.HttpStatus
import reactor.core.publisher.Mono

internal class ThrowableTranslator private constructor(throwable: Throwable) {

    val httpStatus: HttpStatus
    val message: String?

    init {
        this.httpStatus = getStatus(throwable)
        this.message = throwable.message
    }

    private fun getStatus(error: Throwable): HttpStatus {
        return if (error is PathNotFoundException) {
            HttpStatus.BAD_REQUEST
        } else {
            HttpStatus.INTERNAL_SERVER_ERROR
        }
    }

    companion object {

        fun <T : Throwable> translate(throwable: Mono<T>): Mono<ThrowableTranslator> {
            return throwable.flatMap { error -> Mono.just(ThrowableTranslator(error)) }
        }
    }
}

