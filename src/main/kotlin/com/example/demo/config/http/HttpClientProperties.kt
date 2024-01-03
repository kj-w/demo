package com.example.demo.config.http

open class HttpClientProperties(
    var baseUrl: String? = null,
    var responseTimeoutMs: Long = 3000,
)
