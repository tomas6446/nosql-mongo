package com.mongodb.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * @author Tomas Kozakas
 */
@Document("users")
data class User(
        @Id
        val userId: ObjectId = ObjectId(),
        val username: String,
        var password: String,
        val email: String
)
