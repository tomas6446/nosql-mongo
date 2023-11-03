package com.mongodb.service

import com.mongodb.model.User
import com.mongodb.model.UserLogin
import com.mongodb.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

/**
 * @author Tomas Kozakas
 */
@Service
class UserService(private val userRepository: UserRepository) {
    fun getAll(): List<User> {
        return userRepository.findAll();
    }

    fun registerUser(newUser: User): User {
        newUser.password = BCryptPasswordEncoder().encode(newUser.password)
        return userRepository.save(newUser)
    }

    fun loginUser(userLogin: UserLogin): User {
        val retrievedUser = userRepository.findByUsername(userLogin.username).orElseThrow { RuntimeException("User not found with username: ${userLogin.username}") }
        if (!BCryptPasswordEncoder().matches(userLogin.password, retrievedUser.password)) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found or unauthorized.")
        }
        return retrievedUser
    }
}
