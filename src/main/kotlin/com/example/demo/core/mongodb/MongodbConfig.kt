package com.example.demo.core.mongodb

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.config.EnableReactiveMongoAuditing
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.convert.MappingMongoConverter
import org.springframework.data.mongodb.core.convert.MongoCustomConversions
import org.springframework.data.mongodb.core.mapping.MongoMappingContext
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories

@Configuration
@EnableReactiveMongoAuditing
@EnableReactiveMongoRepositories("com.example.demo.**.mongodb.repository")
class MongodbConfig(
    @Value("\${spring.data.mongodb.database}")
    val database: String,
) : AbstractReactiveMongoConfiguration() {

    override fun getDatabaseName(): String = database

    override fun mappingMongoConverter(
        databaseFactory: ReactiveMongoDatabaseFactory,
        customConversions: MongoCustomConversions,
        mappingContext: MongoMappingContext
    ): MappingMongoConverter {
        return MappingMongoConverter(
            ReactiveMongoTemplate.NO_OP_REF_RESOLVER,
            mappingContext,
        ).apply { afterPropertiesSet() }
    }
}
