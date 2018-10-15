package es.example.config.es

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import org.apache.http.HttpHost
import org.elasticsearch.client.RestClient
import org.elasticsearch.client.RestHighLevelClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ESConfig {

    @Bean
    fun objectMapper(): ObjectMapper {
        val objectMapper = ObjectMapper()
        objectMapper.findAndRegisterModules()
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        return objectMapper
    }

    @Bean
    fun restHighLevelClient(props: ESProperties): RestHighLevelClient {
        val hosts : Array<HttpHost> = props.hosts().toTypedArray()
        return RestHighLevelClient(
                RestClient
                        .builder(*hosts)
                        .setRequestConfigCallback { builderConfig ->
                            builderConfig
                                    .setConnectTimeout(props.connectTimeout.toInt())
                                    .setConnectionRequestTimeout(props.connectionRequestTimeout.toInt())
                                    .setSocketTimeout(props.socketTimeout.toInt())
                        }
                        .setMaxRetryTimeoutMillis(props.maxRetryTimeoutMillis.toInt()))
    }

}