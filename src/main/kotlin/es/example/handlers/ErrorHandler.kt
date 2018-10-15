package es.example.handlers

import es.example.exception.PathNotFoundException
import es.example.model.ErrorResponse
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class ErrorHandler {

    fun notFound(request: ServerRequest): Mono<ServerResponse> {
        return Mono.just(PathNotFoundException(NOT_FOUND)).transform { this.getResponse(it) }
    }

    fun throwableError(error: Throwable): Mono<ServerResponse> {
        logger.error(ERROR_RAISED, error)
        return Mono.just(error).transform { this.getResponse(it) }
    }

    internal fun <T : Throwable> getResponse(monoError: Mono<T>): Mono<ServerResponse> {
        return monoError.transform { ThrowableTranslator.translate(it) }
                .flatMap { translation ->
                    ServerResponse
                            .status(translation.httpStatus)
                            .body(Mono.just(ErrorResponse(translation.message)), ErrorResponse::class.java)
                }
    }

    companion object {

        private val NOT_FOUND = "not found"
        private val ERROR_RAISED = "error raised"
        private val logger = LoggerFactory.getLogger(ErrorHandler::class.java)
    }
}
