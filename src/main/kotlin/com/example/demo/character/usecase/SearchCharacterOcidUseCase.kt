package com.example.demo.character.usecase

import com.example.demo.adapter.http.nexon.NexonAdapter
import com.example.demo.character.controller.message.SearchCharacterOcid
import org.springframework.stereotype.Service

@Service
class SearchCharacterOcidUseCase(
    private val nexonAdapter: NexonAdapter,
) {
    suspend fun search(request: SearchCharacterOcid.PathVariable): SearchCharacterOcid.Response {
        return nexonAdapter.searchCharacterId(request.name)
            .let {
                SearchCharacterOcid.Response(
                    name = request.name,
                    ocid = it.ocid,
                )
            }
    }
}
