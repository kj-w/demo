package com.example.demo.character.controller

import com.example.demo.adapter.http.nexon.NexonAdapter
import com.example.demo.adapter.http.nexon.message.CharacterStatInfo
import com.example.demo.character.controller.message.SearchCharacterOcid
import com.example.demo.character.usecase.SearchCharacterOcidUseCase
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/v1/character")
class CharacterController(
    private val searchCharacterOcidUseCase: SearchCharacterOcidUseCase,
    private val nexonAdapter: NexonAdapter,
) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/{name}")
    suspend fun searchCharacterOcid(
        request: SearchCharacterOcid.PathVariable,
    ): SearchCharacterOcid.Response = searchCharacterOcidUseCase.search(request)

    @GetMapping("/stat/{ocid}")
    suspend fun getCharacterStat(
        @PathVariable ocid: String,
    ): CharacterStatInfo.Response {
        return nexonAdapter.getStat(
            CharacterStatInfo.Request(
                ocid = ocid,
                date = LocalDate.now().minusDays(1),
            ),
        )
    }
}
