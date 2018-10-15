package es.example.config.es

import org.apache.http.HttpHost
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import kotlin.streams.toList

@Configuration
@ConfigurationProperties(prefix = "elasticsearch")
class ESProperties {

    lateinit var hosts: MutableList<String>
    lateinit var connectTimeout: String
    lateinit var connectionRequestTimeout: String
    lateinit var socketTimeout: String
    lateinit var maxRetryTimeoutMillis: String

    fun hosts(): List<HttpHost> {
        return this.hosts
                .stream()
                .map<HttpHost> { HttpHost.create(it) }
                .toList()
    }

}