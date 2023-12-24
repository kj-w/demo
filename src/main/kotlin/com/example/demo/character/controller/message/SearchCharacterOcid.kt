package com.example.demo.character.controller.message

abstract class SearchCharacterOcid {

    data class PathVariable(
        val name: String,
    )

    data class Response(
        val name: String,
        val ocid: String,
    )
}
