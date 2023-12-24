package com.example.demo.adapter.mongodb

import com.example.demo.adapter.mongodb.document.TestDocument
import com.example.demo.adapter.mongodb.repository.MongodbTestRepository
import kotlinx.coroutines.reactive.awaitFirstOrNull
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query.query
import org.springframework.stereotype.Component

@Component
class MongodbTestAdapter(
    private val mongodbTestRepository: MongodbTestRepository,
    private val reactiveMongoTemplate: ReactiveMongoTemplate,
) {

    /* repository 버전 */
    suspend fun findById(id: String): TestDocument {
        return mongodbTestRepository.findById(id)
            ?: throw RuntimeException("cannot find test id: $id")
    }

    /* mongoTemplate 버전 */
    suspend fun findByIdV2(id: String): TestDocument {
        return reactiveMongoTemplate.find(
            query(
                where(TestDocument::id.name).`is`(id)
            ),
            TestDocument::class.java,
        ).awaitFirstOrNull()
            ?: throw RuntimeException("cannot find test id: $id")
    }
}
