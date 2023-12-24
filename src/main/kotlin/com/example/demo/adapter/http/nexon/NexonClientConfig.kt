package com.example.demo.adapter.http.nexon

import com.example.demo.core.http.HttpClientProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class NexonClientConfig {

    @Bean
    @ConfigurationProperties("adapter.http.nexon")
    fun nexonClientProperties() = NexonClientProperties()

}
data class NexonClientProperties(
    var nxOpenApiKey: String? = null,
) : HttpClientProperties()
