package es.example.routers

import es.example.handlers.ApiHandler
import es.example.handlers.ErrorHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.ALL
import org.springframework.web.reactive.function.server.router

@Configuration
class ApiRouter(val apiHandler: ApiHandler, val errorHandler: ErrorHandler) {

    @Bean
    fun apiRoute() = router {
        GET("/es-get", apiHandler::getRequestES)
        GET("/es-search", apiHandler::getSearchRequestES)
    }.andOther(router {
        accept(ALL) { errorHandler.notFound(it) }
    })
}
