package es.example.services

import org.elasticsearch.action.get.GetResponse
import org.elasticsearch.action.search.SearchResponse
import reactor.core.publisher.Mono

interface ExampleService {

    fun exampleGetRequest(): Mono<GetResponse>

    fun exampleSearchRequest(): Mono<SearchResponse>

}
