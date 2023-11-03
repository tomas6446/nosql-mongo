package com.mongodb.controller

import com.mongodb.model.User
import com.mongodb.model.UserLogin
import com.mongodb.service.UserService
import org.springframework.web.bind.annotation.*

/**
 * @author Tomas Kozakas
 */
@RestController
@RequestMapping("/api/v1/user")
class UserController(private val userService: UserService) {
    @GetMapping
    fun getUsers(): List<User> {
        return userService.getAll();
    }

    @PostMapping("/login")
    fun loginUser(@RequestBody userLogin: UserLogin): User {
        return userService.loginUser(userLogin);
    }

    @PostMapping("/register")
    fun registerUser(@RequestBody user: User): User {
        return userService.registerUser(user)
    }
}
