package com.mongodb.model

import org.bson.types.ObjectId
import java.time.LocalDateTime

/**
 * @author Tomas Kozakas
 */
data class RentUpdate(
        val rentId: ObjectId = ObjectId.get(),
        val returnDate: LocalDateTime
)
