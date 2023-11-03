package com.mongodb.repository

import com.mongodb.model.Rent
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.Aggregation
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

/**
 * @author Tomas Kozakas
 */
@Repository
interface RentRepository : MongoRepository<Rent, ObjectId> {
    @Aggregation(
        pipeline = [
            "{ \$match: {'userId': ?0} }",
            "{ \$group: {_id: null, total: {\$sum: '\$invoice.totalAmount'}} }"
        ]
    )
    fun getTotalAmountByUserId(userId: ObjectId): Double

    @Aggregation(
        pipeline = [
            "{ \$match: {'userId': ?0, 'returnDate': null} }",
            "{ \$count: 'currentlyRentedCarsCount' }"
        ]
    )
    fun getCurrentlyRentedCarsCountByUserId(userId: ObjectId): Int

    @Query(value = "{ 'userId': ?0 }", fields = "{ 'invoice': 1, '_id': 1}")
    fun findInvoicesByUserId(userId: ObjectId): List<Rent>
}
