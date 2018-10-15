package es.example.handlers

import es.example.services.ExampleService
import org.elasticsearch.action.get.GetResponse
import org.elasticsearch.action.search.SearchResponse
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class ApiHandler(private val exampleService: ExampleService) {

    fun getRequestES(request: ServerRequest): Mono<ServerResponse> {
        val getResponse: Mono<GetResponse> = exampleService.exampleGetRequest()
        return getResponse
                .flatMap { response ->
                    ServerResponse.ok()
                            .contentType(APPLICATION_JSON)
                            .body(fromObject(response))
                }
    }

    fun getSearchRequestES(request: ServerRequest) : Mono<ServerResponse> {
        val searchResponse: Mono<SearchResponse> = exampleService.exampleSearchRequest()
        return searchResponse
                .flatMap { response ->
                    ServerResponse.ok()
                            .contentType(APPLICATION_JSON)
                            .body(fromObject(response))
                }
    }

}