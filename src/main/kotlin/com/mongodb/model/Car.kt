package com.mongodb.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

/**
 * @author Tomas Kozakas
 */
@Document("cars")
data class Car(
        @Id
        val carId: ObjectId = ObjectId(),
        val model: String,
        val make: String,
        val year: Int,
        val price: Double,
        var status: CarStatus,
)
