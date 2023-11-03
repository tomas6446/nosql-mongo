package com.mongodb.service

import com.mongodb.model.*
import com.mongodb.repository.CarRepository
import com.mongodb.repository.RentRepository
import org.bson.types.ObjectId
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.time.Duration

/**
 * @author Tomas Kozakas
 */
@Service
class RentService(
    private val rentRepository: RentRepository,
    private val carRepository: CarRepository
) {
    fun getRents(): List<Rent> {
        return rentRepository.findAll();
    }

    fun getTotalAmountByUserId(userId: ObjectId): Double {
        return rentRepository.getTotalAmountByUserId(userId)
    }

    fun getCurrentlyRentedCarsCountByUserId(userId: ObjectId): Int {
        return rentRepository.getCurrentlyRentedCarsCountByUserId(userId)
    }

    fun saveRent(rent: Rent): ResponseEntity<String> {
        val car = carRepository.findById(rent.carId)
            .orElseThrow { RuntimeException("Car not found with id: $rent.carId") }
        if (car.status == CarStatus.RENTED) {
            return ResponseEntity("The car is not available for rent.", HttpStatus.CONFLICT)
        }
        rent.invoice = generateInvoice(rent, car)
        car.status = CarStatus.RENTED
        rentRepository.save(rent)
        carRepository.save(car)
        return ResponseEntity("Rent Saved.", HttpStatus.OK)
    }

    private fun generateInvoice(rent: Rent, car: Car): Invoice {
        val daysRented = if (rent.returnDate == null) 1 else Duration.between(rent.rentDate, rent.returnDate).toDays()
        val totalAmount = car.price * daysRented
        return Invoice(
            "Car rent invoice",
            rent.rentDate,
            rent.returnDate,
            totalAmount,
            PaymentStatus.UNPAID
        )
    }

    fun deleteRent(rent: Rent): ResponseEntity<String> {
        rentRepository.delete(rent)
        return ResponseEntity("Rent Deleted.", HttpStatus.OK)
    }

    fun updateRent(rentUpdate: RentUpdate): ResponseEntity<String> {
        val existingRent = rentRepository.findById(rentUpdate.rentId)
            .orElseThrow { RuntimeException("Rent not found with id: ${rentUpdate.rentId}") }
        val car = carRepository.findById(existingRent.carId)
            .orElseThrow { RuntimeException("Car not found with id: ${existingRent.rentId}") }
        if (car.status == CarStatus.AVAILABLE) {
            return ResponseEntity("The car is not being rented.", HttpStatus.CONFLICT)
        }
        existingRent.returnDate = rentUpdate.returnDate
        existingRent.invoice = generateInvoice(existingRent, car)
        car.status = CarStatus.AVAILABLE
        rentRepository.save(existingRent)
        carRepository.save(car)
        return ResponseEntity("Rent updated.", HttpStatus.OK)
    }

    fun findInvoicesByUserId(userId: ObjectId): List<Rent> {
        return rentRepository.findInvoicesByUserId(userId);
    }

}
