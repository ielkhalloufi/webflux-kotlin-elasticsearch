package es.example.config.es

import org.elasticsearch.action.ActionListener
import org.elasticsearch.action.get.GetRequest
import org.elasticsearch.action.get.GetResponse
import org.elasticsearch.action.search.SearchRequest
import org.elasticsearch.action.search.SearchResponse
import org.elasticsearch.client.RestHighLevelClient
import reactor.core.publisher.Mono

object ESExtensions {

    fun RestHighLevelClient.monoAsyncGet(getRequest: GetRequest): Mono<GetResponse> = Mono.create { sink ->
        this.getAsync(getRequest, ActionListener.wrap(
                { response -> sink.success(response) },
                { failure -> sink.error(failure) }
        ))
    }

    fun RestHighLevelClient.monoAsyncSearch(searchRequest: SearchRequest): Mono<SearchResponse> = Mono.create { sink ->
        this.searchAsync(searchRequest, ActionListener.wrap(
                { response -> sink.success(response) },
                { failure -> sink.error(failure) }
        ))
    }

}