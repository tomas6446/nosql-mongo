package com.mongodb.controller

import com.mongodb.model.Rent
import com.mongodb.model.RentUpdate
import com.mongodb.service.RentService
import org.bson.types.ObjectId
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * @author Tomas Kozakas
 */
@RestController
@RequestMapping("/api/v1/rent")
class RentController(private val rentService: RentService) {
    @GetMapping
    fun getRents(): List<Rent> {
        return rentService.getRents();
    }

    @GetMapping("/user/{userId}")
    fun getTotalAmountByUserId(@PathVariable userId: ObjectId): Double {
        return rentService.getTotalAmountByUserId(userId)
    }

    @GetMapping("/count/{userId}")
    fun getCurrentlyRentedCarsCountByUserId(@PathVariable userId: ObjectId): Int {
        return rentService.getCurrentlyRentedCarsCountByUserId(userId)
    }

    @PostMapping
    fun saveRent(@RequestBody rent: Rent): ResponseEntity<String> {
        return rentService.saveRent(rent)
    }

    @DeleteMapping
    fun deleteRent(@RequestBody rent: Rent): ResponseEntity<String> {
        return rentService.deleteRent(rent)
    }

    @PutMapping
    fun updateRent(@RequestBody rentUpdate: RentUpdate): ResponseEntity<String> {
        return rentService.updateRent(rentUpdate)
    }

    @GetMapping("/invoice/{userId}")
    fun getAllInvoices(@PathVariable userId: ObjectId): List<Rent> {
        return rentService.findInvoicesByUserId(userId);
    }
}
