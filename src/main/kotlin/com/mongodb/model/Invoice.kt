package com.mongodb.model

import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

/**
 * @author Tomas Kozakas
 */
@Document
data class Invoice(
        val invoiceName: String,
        val dateIssued: LocalDateTime? = null,
        val dueDate: LocalDateTime? = null,
        val totalAmount: Double,
        val paymentStatus: PaymentStatus,
)
