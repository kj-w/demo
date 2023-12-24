package com.example.demo.adapter.mongodb.document

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("test")
data class TestDocument(
    @Id val id: String? = null,
    val content: String,
)
