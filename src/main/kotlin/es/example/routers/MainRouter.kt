package es.example.routers

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction


@Configuration
class MainRouter {

    @Bean
    fun mainRoute(apiRoute: ApiRouter): RouterFunction<*> {
        return apiRoute
                .apiRoute()
                .andOther(StaticRouter.doRoute())
    }
}
