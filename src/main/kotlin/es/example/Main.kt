package es.example

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.web.reactive.config.EnableWebFlux

@EnableConfigurationProperties
@SpringBootApplication
@EnableWebFlux
class Main

fun main(args: Array<String>) {
    runApplication<Main>(*args)
}
