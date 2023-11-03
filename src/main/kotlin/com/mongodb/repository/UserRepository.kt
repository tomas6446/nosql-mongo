package com.mongodb.repository

import com.mongodb.model.User
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.*

/**
 * @author Tomas Kozakas
 */
@Repository
interface UserRepository : MongoRepository<User, ObjectId> {
    fun findByUsername(username: String): Optional<User>
}
