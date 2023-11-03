package com.mongodb.controller

import com.mongodb.model.Car
import com.mongodb.service.CarService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * @author Tomas Kozakas
 */
@RestController
@RequestMapping("/api/v1/car")
class CarController(private val carService: CarService) {
    @GetMapping
    fun getCars(): List<Car> {
        return carService.getCars();
    }

    @PostMapping
    fun saveCar(@RequestBody car: Car): ResponseEntity<String>{
        return carService.saveCar(car)
    }

    @DeleteMapping
    fun deleteCar(@RequestBody car: Car): ResponseEntity<String> {
        return carService.deleteCar(car)
    }
}
