package com.example.demo.adapter.http.nexon

import com.example.demo.adapter.http.nexon.message.SearchCharacterId
import io.netty.handler.logging.LogLevel
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import reactor.netty.http.client.HttpClient
import reactor.netty.transport.logging.AdvancedByteBufFormat
import java.time.Duration

@Component
class NexonAdapter(
    webClientBuilder: WebClient.Builder,
    nexonClientProperties: NexonClientProperties,
) {

    private val webClient: WebClient = webClientBuilder.clientConnector(
        ReactorClientHttpConnector(
            HttpClient
                .create()
                .responseTimeout(Duration.ofMillis(nexonClientProperties.responseTimeoutMs))
                .wiretap(
                    HttpClient::class.qualifiedName!!,
                    LogLevel.DEBUG,
                    AdvancedByteBufFormat.TEXTUAL,
                ),
        )
    )
        .defaultHeader("x-nxopen-api-key", nexonClientProperties.nxOpenApiKey)
        .baseUrl(nexonClientProperties.baseUrl!!)
        .build()

    suspend fun searchCharacterId(name: String): SearchCharacterId.Response {
        return webClient
            .get()
            .uri {
                it.path("/maplestory/v1/id")
                    .queryParam("character_name", name)
                    .build()
            }
            .retrieve()
            .awaitBody<SearchCharacterId.Response>()
    }
}
