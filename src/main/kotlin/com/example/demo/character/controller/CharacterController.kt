package com.example.demo.character.controller

import com.example.demo.character.controller.message.SearchCharacterOcid
import com.example.demo.character.usecase.SearchCharacterOcidUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CharacterController(
    private val searchCharacterOcidUseCase: SearchCharacterOcidUseCase,
) {

    @GetMapping("/v1/character/{name}")
    suspend fun searchCharacterOcid(
        request: SearchCharacterOcid.PathVariable,
    ): SearchCharacterOcid.Response = searchCharacterOcidUseCase.search(request)
}
