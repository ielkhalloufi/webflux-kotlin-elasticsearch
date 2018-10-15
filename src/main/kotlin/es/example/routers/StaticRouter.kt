package es.example.routers

import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.web.reactive.function.server.RouterFunction

import org.springframework.web.reactive.function.server.RouterFunctions.resources

internal object StaticRouter {

    fun doRoute(): RouterFunction<*> {
        return resources("/**", ClassPathResource("public/") as Resource)
    }
}
