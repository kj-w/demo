package com.example.demo.adapter.mongodb.repository

import com.example.demo.adapter.mongodb.document.TestDocument
import org.springframework.data.repository.kotlin.CoroutineCrudRepository

interface MongodbTestRepository : CoroutineCrudRepository<TestDocument, String>
