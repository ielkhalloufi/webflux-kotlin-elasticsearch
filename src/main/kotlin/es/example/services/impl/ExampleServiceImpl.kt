package es.example.services.impl

import es.example.config.es.ESExtensions.monoAsyncGet
import es.example.config.es.ESExtensions.monoAsyncSearch
import es.example.services.ExampleService
import org.elasticsearch.action.get.GetRequest
import org.elasticsearch.action.get.GetResponse
import org.elasticsearch.action.search.SearchRequest
import org.elasticsearch.action.search.SearchResponse
import org.elasticsearch.client.RestHighLevelClient
import org.elasticsearch.index.query.QueryBuilders
import org.elasticsearch.search.builder.SearchSourceBuilder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class ExampleServiceImpl @Autowired constructor(private val restHighLevelClient: RestHighLevelClient) : ExampleService {

    override fun exampleGetRequest(): Mono<GetResponse> {
        val getRequest = GetRequest("index_name")
        return restHighLevelClient.monoAsyncGet(getRequest)
    }

    override fun exampleSearchRequest(): Mono<SearchResponse> {
        val searchRequest = SearchRequest("index_name")
        val searchSourceBuilder = SearchSourceBuilder()
        searchSourceBuilder.query(QueryBuilders.matchAllQuery())
        searchRequest.source(searchSourceBuilder)
        return restHighLevelClient.monoAsyncSearch(searchRequest)
    }

}
