package com.mongodb.repository

import com.mongodb.model.Car
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

/**
 * @author Tomas Kozakas
 */
@Repository
interface CarRepository : MongoRepository<Car, ObjectId> {
}
