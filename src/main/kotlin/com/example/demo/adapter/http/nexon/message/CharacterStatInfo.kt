package com.example.demo.adapter.http.nexon.message

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.LocalDate
import java.time.OffsetDateTime

abstract class CharacterStatInfo {
    data class Request(
        val ocid: String,
        val date: LocalDate,
    )

    @JsonNaming(SnakeCaseStrategy::class)
    data class Response(
        val date: OffsetDateTime,
        val characterClass: String,
        val finalStat: List<FinalStatItem>,
        val remainAp: Int,
    )

    @JsonNaming(SnakeCaseStrategy::class)
    data class FinalStatItem(
        val statName: String,
        val statValue: String,
    )
}
