package com.mongodb.service

import com.mongodb.model.Car
import com.mongodb.repository.CarRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service


@Service
class CarService(private val carRepository: CarRepository) {
    fun getCars(): List<Car> {
        return carRepository.findAll();
    }

    fun saveCar(car: Car): ResponseEntity<String> {
        carRepository.save(car)
        return ResponseEntity("Car Saved.", HttpStatus.OK)
    }

    fun deleteCar(car: Car): ResponseEntity<String> {
        carRepository.delete(car)
        return ResponseEntity("Car Deleted.", HttpStatus.OK)
    }
}
