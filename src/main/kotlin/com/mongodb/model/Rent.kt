package com.mongodb.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

/**
 * @author Tomas Kozakas
 */
@Document("rents")
data class Rent(
        @Id
        val rentId: ObjectId = ObjectId(),
        val userId: ObjectId = ObjectId.get(),
        val carId: ObjectId = ObjectId.get(),
        val rentDate: LocalDateTime? = null,
        var returnDate: LocalDateTime? = null,
        var invoice: Invoice? = null
)
